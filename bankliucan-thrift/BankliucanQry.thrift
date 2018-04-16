/**
* 文件名为BankliucanQry.thrift
* 实现功能：创建一个查询结果struct和一个服务接口service
* 基于：thrift-0.9.2
**/
namespace java com.thrift
struct AccountResult {
        /**
        *返回码, 1成功，0失败
        */
        1:i32 code;
        /**
        *响应信息
        */
        2:string msg;
}
service BankliucanQry{
        /**
        * 根据accountid查询用户的account余额，成功返回余额，失败返回失败信息
        * @param accountid账号id
        */
        AccountResult serachMoneyByAccountId(1:i32 accountId)
}