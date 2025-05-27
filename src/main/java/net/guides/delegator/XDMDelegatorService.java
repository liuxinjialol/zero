// package net.guides.delegator;

// import org.springframework.stereotype.*;
// import org.springframework.web.client.*;
// import org.springframework.beans.factory.annotation.*;
// import com.huawei.innovation.rdm.delegate.config.*;
// import com.huawei.innovation.rdm.delegate.exception.*;
// import org.springframework.util.*;
// import com.huawei.innovation.rdm.coresdk.basic.vo.*;
// import org.springframework.http.*;
// import com.huawei.innovation.rdm.delegate.util.*;
// import java.util.function.*;
// import java.util.stream.*;
// import java.util.*;
// import org.slf4j.*;

// @Service
// public class XDMDelegatorService
// {
//     private static final Logger log;
//     private Set<String> xdmEntitySet;
//     @Autowired(required = false)
//     @Qualifier("xdmRestTemplate")
//     private RestTemplate restTemplate;
//     @Autowired(required = false)
//     private DelegatorConfig config;
//     @Autowired(required = false)
//     private XdmTokenService xdmTokenService;
//     @Autowired(required = false)
//     private SSLRestTemplate sslRestTemplate;
    
//     public Object invoke(final String moduleRelatedApp, final String moduleName, final String methodName, final Object param) throws RdmDelegateException {
//         return this.invoke(moduleRelatedApp, moduleName, methodName, param, Collections.emptyMap());
//     }
    
//     public Object invoke(final String moduleRelatedApp, final String moduleName, final String methodName, final Object param, final Map<String, String> headerParam) throws RdmDelegateException {
//         final String url = this.getURL(moduleRelatedApp, moduleName, methodName, null);
//         final RDMParamVO<Object> paramVO = (RDMParamVO<Object>)new RDMParamVO();
//         paramVO.setParams(param);
//         final RDMResultVO rdmResultVO = this.exchange(url, paramVO, headerParam);
//         if ("FAIL".equals(rdmResultVO.getResult())) {
//             XDMDelegatorService.log.error("call delegator failed, error information is {}", (Object)rdmResultVO);
//             throw new RdmDelegateException("IIT.61003006", "call delegator failed, please check...");
//         }
//         return rdmResultVO.getData();
//     }
    
//     public Object invoke(final String moduleRelatedApp, final String moduleName, final String methodName, final Object param, final RDMPageVO pageVO) throws RdmDelegateException {
//         return this.invoke(moduleRelatedApp, moduleName, methodName, param, pageVO, Collections.emptyMap());
//     }
    
//     public Object invoke(final String moduleRelatedApp, final String moduleName, final String methodName, final Object param, final RDMPageVO pageVO, final Map<String, String> headerParam) throws RdmDelegateException {
//         final String url = this.getURL(moduleRelatedApp, moduleName, methodName, pageVO);
//         final RDMParamVO<Object> paramVO = (RDMParamVO<Object>)new RDMParamVO();
//         paramVO.setParams(param);
//         final RDMResultVO rdmResultVO = this.exchange(url, paramVO, headerParam);
//         pageVO.setTotalCount((long)rdmResultVO.getPageInfo().getTotalRows());
//         pageVO.setTotalPages(rdmResultVO.getPageInfo().getTotalPages());
//         return rdmResultVO.getData();
//     }
    
//     private RDMResultVO exchange(final String url, final Object param, final Map<String, String> headerParam) {
//         final Map<String, String> headers = RestTemplateUtil.getHeadersMap(this.config.getDomain(), "application/json;charset=utf8");
//         headers.put("X-Auth-Token", this.xdmTokenService.getToken());
//         headers.put("tenantId", String.valueOf(TenantUtil.getTenantId()));
//         if (!ObjectUtils.isEmpty((Object)headerParam)) {
//             headers.putAll(headerParam);
//             headerParam.clear();
//         }
//         final HttpEntity<String> requestEntity = RestTemplateUtil.buildRequestEntity(XDMDelegatorJsonUtil.object2string(param), headers);
//         final boolean isUrl = RestTemplateUtil.isUrl(url);
//         if (!isUrl) {
//             throw new RdmDelegateException("IIT.61003001", "The param url can't be empty!");
//         }
//         final RDMResultVO rdmResultVO = this.postForEntity(url, requestEntity);
//         if (!CollectionUtils.isEmpty((Collection)rdmResultVO.getErrors())) {
//             final List<RDMErrorVO> errorVOList = (List<RDMErrorVO>)rdmResultVO.getErrors();
//             final StringBuffer messages = new StringBuffer();
//             for (final RDMErrorVO errorVO : errorVOList) {
//                 messages.append(errorVO.getMessage()).append(";");
//             }
//             XDMDelegatorService.log.error("RDM-SDK XDMDelegatorService execute fail:{}", (Object)messages);
//             final String tranceId = String.valueOf(errorVOList.get(0).getArgs().get(0));
//             throw new RdmDelegateException(errorVOList.get(0).getCode(), messages.toString(), tranceId);
//         }
//         return rdmResultVO;
//     }
    
//     private RDMResultVO postForEntity(final String url, final Object request) {
//         ResponseEntity<Object> responseEntity;
//         if (Boolean.TRUE.equals(this.config.getSsl())) {
//             responseEntity = (ResponseEntity<Object>)this.sslRestTemplate.customRestTemplateWithSsl().postForEntity(url, request, (Class)Object.class, new Object[0]);
//         }
//         else {
//             responseEntity = (ResponseEntity<Object>)this.restTemplate.postForEntity(url, request, (Class)Object.class, new Object[0]);
//         }
//         return ResponseHandlerUtil.handleReturnValue(responseEntity.getBody());
//     }
    
//     private String getURL(final String strUrl, final String moduleName, final String methodName, final RDMPageVO pageVO) {
//         String url = String.format(Locale.ROOT, "%s/%s/%s/%s/%s/%s", this.config.getDomain(), this.config.getSubAppId(), this.getServiceType(moduleName), strUrl, moduleName, methodName);
//         if (!ObjectUtils.isEmpty((Object)pageVO)) {
//             url = String.format(Locale.ROOT, "%s/%s/%s", url, pageVO.getPageSize(), pageVO.getCurPage());
//         }
//         return url;
//     }
    
//     private String getServiceType(final String moduleName) {
//         if (Objects.isNull(this.xdmEntitySet)) {
//             this.loadXDMEntitySet();
//         }
//         if (CollectionUtils.isEmpty((Collection)this.xdmEntitySet) || !this.xdmEntitySet.contains(moduleName)) {
//             return this.config.getServiceType();
//         }
//         return this.config.getXdmServiceType();
//     }
    
//     private void loadXDMEntitySet() {
//         try {
//             this.xdmEntitySet = ClassUtil.getXDMEntityClasses().stream().map((Function<? super Object, ?>)Class::getSimpleName).collect((Collector<? super Object, ?, Set<String>>)Collectors.toSet());
//         }
//         catch (Exception exception) {
//             XDMDelegatorService.log.error("Load XDM Entity Set Fail");
//             this.xdmEntitySet = new HashSet<String>();
//         }
//     }
    
//     static {
//         log = LoggerFactory.getLogger((Class)XDMDelegatorService.class);
//     }
// }
