package com.adamin.appserver.util;

import java.util.Map;

public class RSATester {

    static String publicKey;
    static String privateKey;

    public static void main(String[] args) throws Exception {
        //获得公钥和私钥
        try {
            Map<String, Object> keyMap = RSAUtils.genKeyPair();
            publicKey = RSAUtils.getPublicKey(keyMap);
//            privateKey = RSAUtils.getPrivateKey(keyMap);
//            privateKey="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALnRynMQNrDcaCeiKUMMHeqz2Y45ipZUq4RUqHR1EBUHsGLmav/AWE/wSGhb94ExJkLYATQfl44klFaCyy4UOerGWjhOwDxDJobeJS4s5AlmKSkzYi9V11ZCarWc02qmHJLbokM0/l5x+CQ6p3lZiqUOpoXOJRIEsB61mYeyulTHAgMBAAECgYARIZBCj2obYBaSINchAUr4yl/yRsfKGEA/0PY+1lQHPFA8rfWLYpZBuGtUg7YLyRY372YdAhSc3C5401IcC21TWYLVlRat32McDly5gQuoASknmDawc19p4xRxKILAWk+HDvR4d7h6hJCOSkvVQiERYsnecvtbCryvyisk+9DU4QJBANpU9B0P75Ys0xufI6LzfVQzMmvu1IHJcI/Gkfj7wN3q1xLEZcuJfY9QAhW38aumFypRup+nfTGoQcuIcwAU9+8CQQDZ4N3UzWjOfaqLqeRUrHTRFeEHFBQnBUj+C8Qupo/kKeEidPslyzf0PLs7z26uufNU+4KF816AgVumPCouX9ipAkBy0i63ggP8NIKpih3Rur/0oiAoRygXQXsjAckO6kdZ7gFB62uliR8xWWBfT6eXH/ga13SQNiNPFC/V/GKObnidAkAXinVNbdg6H1fS+UtDXtLMWInUwvWedbK6SDktNS/s3h2GJI+SOM/V4s30G2iBFnRVJUun/3Chf9iQMIX6Nr7pAkEAxO45aX1KkxZgZZqRlM5erj40AEH9Y38P8k6/2/Y6XUunItid1Tg+jw9ZM8XZpvf10bilghXJwx2WNgCQG4tmgA==";
//             privateKey="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJinw+NS73umP7Nv4zM1Xs+ETH5s9l0eghnKwAfwLdDkWPo/v/hzdAi7vs/ulb1Akd9LfdgBObvr0TiBJYSJ4HeL8DAEV6gXRvw8oZpV/h6q04SnXKWprd2+V7wtVlQqmDy+S/COmsjYMLtgzNN6hZE7kj+dVH/Ufy0rW2F/Z1xXAgMBAAECgYAz3dRtmVErBupf0iU00e5bvYyr6+Os8d+sWbnzOISok90zcZONG9UR3pBPX7XarmShpKmmSYxPMClO9HpF1P7BthI7axSaBZLmt0IjcguWc0G2riKAN7mDtyzdOM2aiMzA6zAcY5/hT/W4mI5ahqybofFDnMf4pC8WhBzfC/IWGQJBAPSfHB/57uzfq+KntXE9QxFljwg7g+YAtpGX2nJqARFbsd5yca0TUTmw+TC8G+WEmr8J7S5kBKqluzuEDmX/ZvUCQQCfwYusiYlcGvLVmnB2iGcUqYZ40eRD5GPnluQXQZe91Vaw4nrBs+0eXdBBtqlWT1sxQJ8SG3OP8cOqYmCBtC6bAkBv2p1Spccxeukuv78Ao6GaP9UST5x1PFg+5K/XZ09Lr6D6dVwzZsTaW2jmdu1Omr7joddZQhS8LwIZKb4nt4xNAkBO/86qC3k5aroalrx2YQ1YUlyE8oYGbLJam2s/dNgsr4qm/dfmYasoEQwi8sjaPXPC70m6Eoka7Vrsrqk6endxAkEAtk8Q4OanM7zaDKS9XSA7dhH6/FmpOoNtlTzA/T6+EdipZ8v/Zn4CwBGC+iXUAEV3L9WieT+XnDiM9TW0Tpj+DQ==";
            privateKey="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJinw+NS73umP7Nv4zM1Xs+ETH5s9l0eghnKwAfwLdDkWPo/v/hzdAi7vs/ulb1Akd9LfdgBObvr0TiBJYSJ4HeL8DAEV6gXRvw8oZpV/h6q04SnXKWprd2+V7wtVlQqmDy+S/COmsjYMLtgzNN6hZE7kj+dVH/Ufy0rW2F/Z1xXAgMBAAECgYAz3dRtmVErBupf0iU00e5bvYyr6+Os8d+sWbnzOISok90zcZONG9UR3pBPX7XarmShpKmmSYxPMClO9HpF1P7BthI7axSaBZLmt0IjcguWc0G2riKAN7mDtyzdOM2aiMzA6zAcY5/hT/W4mI5ahqybofFDnMf4pC8WhBzfC/IWGQJBAPSfHB/57uzfq+KntXE9QxFljwg7g+YAtpGX2nJqARFbsd5yca0TUTmw+TC8G+WEmr8J7S5kBKqluzuEDmX/ZvUCQQCfwYusiYlcGvLVmnB2iGcUqYZ40eRD5GPnluQXQZe91Vaw4nrBs+0eXdBBtqlWT1sxQJ8SG3OP8cOqYmCBtC6bAkBv2p1Spccxeukuv78Ao6GaP9UST5x1PFg+5K/XZ09Lr6D6dVwzZsTaW2jmdu1Omr7joddZQhS8LwIZKb4nt4xNAkBO/86qC3k5aroalrx2YQ1YUlyE8oYGbLJam2s/dNgsr4qm/dfmYasoEQwi8sjaPXPC70m6Eoka7Vrsrqk6endxAkEAtk8Q4OanM7zaDKS9XSA7dhH6/FmpOoNtlTzA/T6+EdipZ8v/Zn4CwBGC+iXUAEV3L9WieT+XnDiM9TW0Tpj+DQ==";
            System.err.println("[publickey]: \n" + publicKey);
            System.err.println("[privatekey]: \n" + privateKey);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("e = " + e.getMessage());
        }

        String username = "丁一";
        String idNo = "370101201302310311";
        String qrCode = "4243670226861111111111370000";
        String param = username+"#"+ idNo +"#"+ qrCode;
//        System.out.println("验签结果： \n" + testSign("徐金华#370828198106201653"));
        System.out.println("验签结果： \n" + testSign("370600#76870154#20210316370637071015420611"));

    }

    static boolean testSign(String param) throws Exception {
        String encodedData = RSAUtils.encryptByPrivateKey(param, privateKey);
        System.out.println("encodedata:" + encodedData);

//        String decodedData = RSAUtils.decryptByPublicKey(encodedData, publicKey);
//        System.out.println("decodedata:" + new String(decodedData));

        String sign = RSAUtils.sign(encodedData, privateKey);
        System.err.println("sign:" + sign);

        boolean status = RSAUtils.verify(encodedData, publicKey, sign);
        System.err.println("sign result:" + status);
        return  status ;
    }


}
