package com.gfs.domain.component;

import com.gfs.domain.config.model.VNPayConfig;
import com.gfs.domain.constant.VNPayConstant;
import com.gfs.domain.utils.AESEncryptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("vnPayConfiguration")
public class VNPayConfiguration extends Configurations<VNPayConfig> {
    public static final String VNPAY_CONFIG_KEY = "vnpay_config";
    private static VNPayConfiguration instance;

    public VNPayConfiguration() {
        super();
        instance = this;
    }

    @Override
    public String getKey() {
        return VNPAY_CONFIG_KEY;
    }

    @Override
    public Class<VNPayConfig> getClazz() {
        return VNPayConfig.class;
    }

    public static String getTMN_CODE() {
        return instance.getConfig().getTmn_code();
    }

    public static String getVERSION() {
        return instance.getConfig().getVersion();
    }

    public static String getHASH_SECRET() {
        return AESEncryptor.decrypt(instance.getConfig().getEncryption_key(), VNPayConstant.VNPAY_HASH_SECRET_INIT_VECTOR, instance.getConfig().getHash_secret());
    }

    public static String getPAY_URL() {
        return instance.getConfig().getPay_url();
    }

    public static String getAPI_URL() {
        return instance.getConfig().getApi_url();
    }
}
