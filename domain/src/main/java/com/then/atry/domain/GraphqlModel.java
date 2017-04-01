package com.then.atry.domain;

/**
 * Created by 42524 on 2017/3/30.
 */

public class GraphqlModel {


    /**
     * b : {"id":"1"}
     */

    private BBean b;

    public BBean getB() {
        return b;
    }

    public void setB(BBean b) {
        this.b = b;
    }

    public static class BBean {
        /**
         * id : 1
         */

        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
