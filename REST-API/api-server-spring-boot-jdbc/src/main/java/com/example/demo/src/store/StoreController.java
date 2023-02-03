package com.example.demo.src.store;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.store.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;

    private final StoreProvider storeProvider;

    @Autowired
    public StoreController(StoreService storeService, StoreProvider storeProvider) {
        this.storeService = storeService;
        this.storeProvider = storeProvider;
    }
    /**
     * 가게생성 API
     * [POST] /store
     * @return BaseResponse<PostStoreRes>
     */
    @ResponseBody
    @PostMapping("")
    public BaseResponse<PostStoreRes> createStore(@RequestBody PostStoreReq postStoreReq) {
        try{
            PostStoreRes postStoreRes = storeService.createStore(postStoreReq);
            return new BaseResponse<>(postStoreRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }


    /* 특정 가게 조회 API
     * [GET] /store/:storeId
     * @return BaseResponse<GetStoreRes>
     */
    // Path-variable
    @ResponseBody
    @GetMapping("/{storeId}") // (GET) localhost:9000/store/:storeId
    public BaseResponse<GetStoreRes> getStore(@PathVariable("storeId") BigInteger storeId) {
        try{
            GetStoreRes getStoreRes = storeProvider.getStores(storeId);
            return new BaseResponse<>(getStoreRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 가게 조회 API
     * [GET] /{storeId}
     * 가게 번호 및 이메일 검색 조회 API
     * [GET] /users? Email=
     * @return BaseResponse<List<GetUserRes>>
     */
    //Query String
    @ResponseBody
    @GetMapping("") // (GET) 127.0.0.1:9000/app/store
    public BaseResponse<List<GetStoreRes>> getStores(@RequestParam(required = false) String storeName) {
        try{
            if(storeName == null){
                List<GetStoreRes> getStoresRes = storeProvider.getStores();
                return new BaseResponse<>(getStoresRes);
            }
            List<GetStoreRes> getStoresRes = storeProvider.getStoresByStoreName(storeName);
            return new BaseResponse<>(getStoresRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 가게정보 삭제 API
     * [DELETE] /store/storeId
     * 가게 정보 삭제*
     * * @return BaseResponse<String>
     */
    @ResponseBody
    @PatchMapping("/{storeId}/status")
    public BaseResponse<String> deleteStore(@PathVariable("storeId") BigInteger storeId, @RequestBody PatchStoreReq patchStoreReq) {
        try {
            storeService.deleteStore(patchStoreReq.getStoreId());
            String result = "가게정보 삭제 완료";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /*
     * 가게정보변경 API
     * [PATCH] /store/:storeId
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PatchMapping("/{storeId}")
    public BaseResponse<String> modifyStoreName(@PathVariable("storeId") BigInteger storeId, @RequestBody Store store){
        try {
            /*//jwt에서 idx 추출.
            BigInteger userIdxByJwt = userId;//jwtService.getUserId();
            //userIdx와 접근한 유저가 같은지 확인
            if(userId != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);*/
            //같다면 유저네임 변경
            PatchStoreReq patchStoreReq = new PatchStoreReq(storeId,store.getStoreName());
            storeService.modifyStoreName(patchStoreReq);

            String result = "";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
