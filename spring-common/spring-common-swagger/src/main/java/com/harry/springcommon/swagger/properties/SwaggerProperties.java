package com.harry.springcommon.swagger.properties;

import com.google.common.collect.Lists;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("k8s.demo.swagger")
public class SwaggerProperties {

  /**
   * swagger会解析的包路径
   **/
  private String basePackage = "";
  /**
   * swagger会解析的url规则
   **/
  private List<String> basePath = Lists.newArrayList();
  /**
   * 在basePath基础上需要排除的url规则
   **/
  private List<String> excludePath = Lists.newArrayList();
  /**
   * 标题
   **/
  private String title = "后台APIs";
  /**
   * 描述
   **/
  private String description = "后台APIs";
  /**
   * 版本
   **/
  private String version = "1.0.0";
  /**
   * 许可证
   **/
  private String license = "";
  /**
   * 许可证URL
   **/
  private String licenseUrl = "";
  /**
   * 服务条款URL
   **/
  private String termsOfServiceUrl = "";

  /**
   * host信息
   **/
  private String host = "";
  /**
   * 联系人信息
   */
  private Contact contact = new Contact();
  /**
   * 全局统一鉴权配置
   **/
  private Authorization authorization = new Authorization();

  @Data
  @NoArgsConstructor
  public static class Contact {

    /**
     * 联系人
     **/
    private String name = "";
    /**
     * 联系人url
     **/
    private String url = "";
    /**
     * 联系人email
     **/
    private String email = "";

  }

  @Data
  @NoArgsConstructor
  public static class Authorization {

    private String cliendId="swagger_cliend_id";

    private String clientSecret="swagger_cliend_secret";

    /**
     * 鉴权策略ID，需要和SecurityReferences ID保持一致
     */
    private String name = "";

    /**
     * 需要开启鉴权URL的正则
     */
    private String authRegex = "^.*$";

    /**
     * 作用域名称
     */
    private String scope = "server";

    /**
     * 作用域描述
     */
    private String scopeDesc = "单点登录服务";

    private String tokenUrl = "";
  }

}
