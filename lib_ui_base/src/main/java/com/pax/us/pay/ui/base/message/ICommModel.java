package com.pax.us.pay.ui.base.message;

interface ICommModel {
    /**
     * get response result
     *
     * @param
     */
    //void getData(RespResult result);
    void getSendHandler(RespResult result);

    /**
     * Response interface callback
     *
     * @param <T>
     */
    public interface RespResult<T> {
        void onSuccess();

        void onFailure(RespMessage respMessage);
    }
}
