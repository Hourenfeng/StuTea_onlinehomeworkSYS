let OSS = require('ali-oss')
export let client = new OSS({
  region: 'oss-cn-qingdao',  // 填你的oss所在区域，例如oss-cn-shenzhen
  accessKeyId: 'LTAI5tGxzrKzfRsNYUKLF5mD', // 填你的oss的accessKeyId
  accessKeySecret: 'aOlYDUIqb7LD9AHBtozIvOBVezk7a0', // 填你的oss的accessSecret
  bucket: 'zjy123456' ,// 你创建的路径名称
  endpoint:'oss-cn-qingdao.aliyuncs.com'
})
