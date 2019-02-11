package com.gemepro.api.service.impl;

import com.gemepro.api.dao.DataskyToCusDao;
import com.gemepro.api.entity.DataskyToCusEntity;
import com.gemepro.api.service.DataSkyToCusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fwq
 */
@Service("dataSkyToCusService")
public class DataSkyToCusServiceImpl implements DataSkyToCusService {

    @Autowired
    private DataskyToCusDao dataSkyToCusDao;

    @Override
    public void save(DataskyToCusEntity dataskyToCusEntity) {
        dataSkyToCusDao.save(dataskyToCusEntity);
    }
}
