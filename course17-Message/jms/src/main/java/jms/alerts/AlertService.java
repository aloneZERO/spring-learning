package jms.alerts;

import jms.domain.Spittle;

/**
 * 创建 Spittle 后，通知其他用户
 *
 * @author justZero
 * @since 2019-1-31
 */
public interface AlertService {

    void sendSpittleAlert(Spittle spittle);

    Spittle retrieveSpittleAlert();

}
