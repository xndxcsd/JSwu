package cn.edu.swu.common;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by 西南大学开源协会 陈思定 on 2017/5/30.
 * <p>
 * Email : chensiding@qq.com
 */
public class LoginResponseBean {


    private DataEntity data;
    private boolean success;

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DataEntity getData() {
        return data;
    }

    public boolean isSuccess() {
        return success;
    }

    public class DataEntity {

        private GetUserInfoByUserNameResponseEntity getUserInfoByUserNameResponse;

        public void setGetUserInfoByUserNameResponse(GetUserInfoByUserNameResponseEntity getUserInfoByUserNameResponse) {
            this.getUserInfoByUserNameResponse = getUserInfoByUserNameResponse;
        }

        public GetUserInfoByUserNameResponseEntity getGetUserInfoByUserNameResponse() {
            return getUserInfoByUserNameResponse;
        }

        public class GetUserInfoByUserNameResponseEntity {

            @JsonProperty("return")
            private ReturnXEntity returnX;

            public void setReturnX(ReturnXEntity returnX) {
                this.returnX = returnX;
            }

            public ReturnXEntity getReturnX() {
                return returnX;
            }

            public class ReturnXEntity {

                private boolean success;
                private InfoEntity info;

                public void setSuccess(boolean success) {
                    this.success = success;
                }

                public void setInfo(InfoEntity info) {
                    this.info = info;
                }

                public boolean isSuccess() {
                    return success;
                }

                public InfoEntity getInfo() {
                    return info;
                }

                public class InfoEntity {

                    private AttributesEntity attributes;
                    private String id;

                    public void setAttributes(AttributesEntity attributes) {
                        this.attributes = attributes;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public AttributesEntity getAttributes() {
                        return attributes;
                    }

                    public String getId() {
                        return id;
                    }

                    public class AttributesEntity {

                        private String tgt;
                        @JsonProperty("ACPORGDN")
                        private String ACPORGDN;
                        @JsonProperty("ACPUID")
                        private String ACPUID;
                        @JsonProperty("ACPNICKNAME")
                        private String ACPNICKNAME;
                        @JsonProperty("ACPUSERGROUPDN")
                        private String ACPUSERGROUPDN;
                        @JsonProperty("ACPNAME")
                        private String ACPNAME;
                        @JsonProperty("ACPSCHOOLEMAIL")
                        private String ACPSCHOOLEMAIL;
                        private String e_rdn;

                        public void setTgt(String tgt) {
                            this.tgt = tgt;
                        }

                        public void setACPORGDN(String ACPORGDN) {
                            this.ACPORGDN = ACPORGDN;
                        }

                        public void setACPUID(String ACPUID) {
                            this.ACPUID = ACPUID;
                        }

                        public void setACPNICKNAME(String ACPNICKNAME) {
                            this.ACPNICKNAME = ACPNICKNAME;
                        }

                        public void setACPUSERGROUPDN(String ACPUSERGROUPDN) {
                            this.ACPUSERGROUPDN = ACPUSERGROUPDN;
                        }

                        public void setACPNAME(String ACPNAME) {
                            this.ACPNAME = ACPNAME;
                        }

                        public void setACPSCHOOLEMAIL(String ACPSCHOOLEMAIL) {
                            this.ACPSCHOOLEMAIL = ACPSCHOOLEMAIL;
                        }

                        public void setE_rdn(String e_rdn) {
                            this.e_rdn = e_rdn;
                        }

                        public String getTgt() {
                            return tgt;
                        }

                        public String getACPORGDN() {
                            return ACPORGDN;
                        }

                        public String getACPUID() {
                            return ACPUID;
                        }

                        public String getACPNICKNAME() {
                            return ACPNICKNAME;
                        }

                        public String getACPUSERGROUPDN() {
                            return ACPUSERGROUPDN;
                        }

                        public String getACPNAME() {
                            return ACPNAME;
                        }

                        public String getACPSCHOOLEMAIL() {
                            return ACPSCHOOLEMAIL;
                        }

                        public String getE_rdn() {
                            return e_rdn;
                        }
                    }
                }
            }
        }
    }
}
