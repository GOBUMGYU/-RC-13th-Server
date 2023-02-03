package com.example.demo.src.store;

import com.example.demo.config.BaseException;
import com.example.demo.src.store.model.PatchStoreReq;
import com.example.demo.src.store.model.PostStoreReq;
import com.example.demo.src.store.model.PostStoreRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;
import static com.example.demo.config.BaseResponseStatus.MODIFY_FAIL_USERNAME;

@Service
public class StoreService {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final StoreDao storeDao;
    private final StoreProvider storeProvider;

    public StoreService(StoreDao storeDao, StoreProvider storeProvider) {
        this.storeDao = storeDao;
        this.storeProvider = storeProvider;
    }


    public PostStoreRes createStore(PostStoreReq postStoreReq) throws BaseException {
        try {
            BigInteger storeId = BigInteger.valueOf(storeDao.createStore(postStoreReq));
            return new PostStoreRes(storeId);
        } catch(Exception exception) {
            logger.error("App - createStore Service Error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void modifyStoreName(PatchStoreReq patchStoreReq) throws BaseException {
        try{
            int result = storeDao.modifyStoreName(patchStoreReq);
            if(result == 0){
                throw new BaseException(MODIFY_FAIL_USERNAME);
            }
        } catch(Exception exception){
            logger.error("App - modifyStoreName Service Error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void deleteStore(BigInteger storeId) throws BaseException {
        try{
            BigInteger result = BigInteger.valueOf(storeDao.deleteStore(storeId));
            if(result == BigInteger.valueOf(0)) {
                System.out.println("DELETE_FAIL_StoreName");
            }
        }catch(Exception exception) {
            logger.error("App - deleteUser Service Error", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }


}
