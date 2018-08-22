package com.publiccms.logic.component.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.publiccms.common.api.Config;
import com.publiccms.common.constants.CommonConstants;
import com.publiccms.common.tools.CommonUtils;
import com.publiccms.common.tools.LanguagesUtils;
import com.publiccms.entities.sys.SysSite;
import com.publiccms.logic.component.site.EmailComponent;
import com.publiccms.views.pojo.entities.ExtendField;

/**
 *
 * EmailTemplateConfigComponent 邮件模板配置组件
 *
 */
@Component
public class EmailTemplateConfigComponent implements Config {
    /**
     * 
     */
    public static final String CONFIG_CODE = "email_verification";
    /**
     * 
     */
    public static final String CONFIG_EMAIL_TITLE = "email_title";
    /**
     * 
     */
    public static final String CONFIG_EMAIL_PATH = "email_path";
    /**
     * 
     */
    public static final String CONFIG_EXPIRY_MINUTES = "expiry_minutes";
    /**
     * 
     */
    public static final String CONFIG_CODE_DESCRIPTION = CONFIGPREFIX + CONFIG_CODE;
    /**
     * default expiry minutes
     */
    public static final int DEFAULT_EXPIRY_MINUTES = 30;

    @Autowired
    private ConfigComponent configComponent;

    @Override
    public String getCode(SysSite site, boolean showAll) {
        Map<String, String> config = configComponent.getConfigData(site.getId(), EmailComponent.CONFIG_CODE);
        if (CommonUtils.notEmpty(config) || showAll) {
            return CONFIG_CODE;
        } else {
            return null;
        }
    }

    @Override
    public String getCodeDescription(Locale locale) {
        return LanguagesUtils.getMessage(CommonConstants.applicationContext, locale, CONFIG_CODE_DESCRIPTION);
    }

    @Override
    public List<ExtendField> getExtendFieldList(SysSite site, Locale locale) {
        Map<String, String> config = configComponent.getConfigData(site.getId(), EmailComponent.CONFIG_CODE);
        if (CommonUtils.notEmpty(config)) {
            List<ExtendField> extendFieldList = new ArrayList<>();
            extendFieldList.add(new ExtendField(CONFIG_EMAIL_TITLE, INPUTTYPE_TEXT, false,
                    LanguagesUtils.getMessage(CommonConstants.applicationContext, locale,
                            CONFIG_CODE_DESCRIPTION + CommonConstants.DOT + CONFIG_EMAIL_TITLE),
                    null, null));
            extendFieldList.add(new ExtendField(CONFIG_EMAIL_PATH, INPUTTYPE_TEMPLATE, false,
                    LanguagesUtils.getMessage(CommonConstants.applicationContext, locale,
                            CONFIG_CODE_DESCRIPTION + CommonConstants.DOT + CONFIG_EMAIL_PATH),
                    null, null));
            extendFieldList.add(new ExtendField(CONFIG_EXPIRY_MINUTES, INPUTTYPE_NUMBER, false,
                    LanguagesUtils.getMessage(CommonConstants.applicationContext, locale,
                            CONFIG_CODE_DESCRIPTION + CommonConstants.DOT + CONFIG_EXPIRY_MINUTES),
                    null, "30"));
            return extendFieldList;
        } else {
            return null;
        }
    }
}
