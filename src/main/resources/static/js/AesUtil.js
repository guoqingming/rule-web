AesUtil = {

}
AesUtil.key = function(){
    var key = '0123456789abcdef';
    key = CryptoJS.enc.Utf8.parse(key);
    return key;
}
AesUtil.iv = function () {
    var iv = '0123456789abcdef';
    iv = CryptoJS.enc.Utf8.parse(iv);
    return iv;
};
AesUtil.encrypt = function (str) {

// // 密钥 16 位
//     var key = '0123456789abcdef';
// // 初始向量 initial vector 16 位
//     var iv = '0123456789abcdef';
// // key 和 iv 可以一致
//
//     key = CryptoJS.enc.Utf8.parse(key);
//     iv = CryptoJS.enc.Utf8.parse(iv);

    var encrypted = CryptoJS.AES.encrypt(str, AesUtil.key(), {
        iv: AesUtil.iv(),
        mode: CryptoJS.mode.CBC,
        padding: CryptoJS.pad.Pkcs7
    });

// 转换为字符串
    encrypted = encrypted.toString();

    return encrypted;

}

AesUtil.decript = function (encrypted) {
    // mode 支持 CBC、CFB、CTR、ECB、OFB, 默认 CBC
// padding 支持 Pkcs7、AnsiX923、Iso10126
// 、NoPadding、ZeroPadding, 默认 Pkcs7, 即 Pkcs5

    var decrypted = CryptoJS.AES.decrypt(encrypted, AesUtil.key(), {
        iv: AesUtil.iv(),
        mode: CryptoJS.mode.CBC,
        padding: CryptoJS.pad.Pkcs7
    });

// 转换为 utf8 字符串
    decrypted = CryptoJS.enc.Utf8.stringify(decrypted);
    return decrypted;
}