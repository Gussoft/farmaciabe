package com.gussoft.farmaciabe.core.utils;

public class Constrains {

    public static final String MESSAGE_NOT_FOUND = "{0} no Encontrado : {1}";

    public static final String FORMAT_DATE = "dd/MM/yyyy HH:mm";

    public static final String[] MAINTENANCE = {
            "/v1/customer/**", "/v1/employee/**", "/v1/laboratory/**", "/v1/presentation/**",
            "/v1/product/**", "/v1/sales/**", "/v1/shopping/**", "/v1/supplier/**", "/v1/api/**"
    };
    public static final String[] CUSTOMER_GET = {
            "/v1/customer", "/v1/customer/{id}", "/v1/customer/page/{page}/{size}"
    };

    public static final String[] EMPLOYEE_GET = {
            "/v1/employee", "/v1/customer/", "/v1/employee/{id}"
    };
    public static final String[] READ = {
            "/v2/customer", "/v2/employee", "/v2/laboratory", "/v2/presentation",
            "/v2/product", "/v2/sales", "/v2/shopping", "/v2/supplier"
    };
    public static final String[] AUTH = {
            "/v1/users/**", "/v1/users", "/v1/role/**", "/v1/role/**"
    };

    public static final String[] SWAGGER = {
            "/swagger-ui/**", "/swagger-resources", "/open-api/**",
            "/open-api/swagger-ui-custom.html", "/open-api/api-docs", "/swagger-ui.html"
    };

}
