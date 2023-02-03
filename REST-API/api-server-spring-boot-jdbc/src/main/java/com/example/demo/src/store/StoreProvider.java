package com.example.demo.src.store;

import com.example.demo.config.BaseException;
import com.example.demo.src.store.model.GetStoreRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class StoreProvider {
    final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final StoreDao storeDao;


    public StoreProvider(StoreDao storeDao) {
        this.storeDao = storeDao;
    }

    public List<GetStoreRes> getStores() throws BaseException {
        try{
            List<GetStoreRes> getStoreRes = storeDao.getStores();
            return getStoreRes;
        }
        catch (Exception exception) {
            logger.error("App - getsotreRes Provider Error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //특정 가게 조회 API
    public GetStoreRes getStores(BigInteger storeId) throws BaseException {
        try {
            GetStoreRes getStoreRes = storeDao.getStore(storeId);
            return getStoreRes;
        } catch (Exception exception) {
            logger.error("App - getStore Provider Error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }
    public List<GetStoreRes> getStoresByStoreName(String storeName) throws BaseException {
        try {
            List<GetStoreRes> getStoresRes = storeDao.getStoresByStoreName(storeName);
            return getStoresRes;
        } catch (Exception exception) {
            logger.error("App - getStoreByName Provider Error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }





}
