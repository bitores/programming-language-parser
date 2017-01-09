var fs=require('fs');
var PEG = require("pegjs");

fs.readFile('./demo.pegjs','utf8',function(err,data){
  var parser=PEG.generate(data);
  var result=parser.parse('1+2*3');

  console.log(result);    // 7
});