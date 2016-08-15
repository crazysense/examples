package com.bistel.a3.platform.factory;

import com.bistel.a3.platform.factory.datasource.DataSource;
import com.bistel.a3.platform.factory.datasource.DataSourceConfig;
import com.bistel.a3.platform.factory.datasource.DataSourceType;
import com.bistel.a3.platform.factory.engine.PlatformEngine;
import com.bistel.a3.platform.factory.engine.PlatformType;
import com.bistel.a3.platform.factory.engine.flink.FlinkEngine;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.Map;

/**
 * Created by kidki on 2016-08-15.
 */
public class PlatformFactory implements FactoryBean<PlatformEngine> {
    private PlatformEngine platformEngine;
    private PlatformType platformType;
    private Map<String, DataSource> dataSourceProxy;

    public void setPlatform(String platform) {
        this.platformType = PlatformType.fromValue(platform);
    }

    @Override
    public PlatformEngine getObject() throws Exception {
        if (this.platformEngine == null) {
            createPlatform();
        }
        return this.platformEngine;
    }

    @Override
    public Class<?> getObjectType() {
        return this.platformEngine == null ? PlatformEngine.class : this.platformEngine.getClass();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    private void createPlatform() {
        if (this.platformType == null) {
            throw new IllegalArgumentException("Property 'platform' is required");
        }

        switch (this.platformType) {
            case flink:
                this.platformEngine = new FlinkEngine();
                break;
            case unknown:
            default:
                break;
        }
    }
}
