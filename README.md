**Generated checksums and matched links for part 4 questions**
------------------

 * ***Link:*** https://tools.ietf.org/rfc/rfc3986.txt

   ***Checksum:*** RbwRYKKAw0uSMwmukf8oOg==

* ***Link:*** https://www.ietf.org/rfc/rfc1149.txt

  ***Checksum:*** 5zAjHAcCDH/HsNXfEoVeMA==


**Project**
------------------

* /model 
   There are two models:
   * LinkFile -> which is used for file. LinkFile has three fields: path, content and list for links.
   * LinkContent -> which is used for link. LinkContent has three fields: link for url, content which is fetched from service and cheksum.
   
* /validator
   * LinkValidator -> extends from MainValidator which has three method for valid url check, port check and parameter check.
 
* /checksum
   * MD5Checksum -> implemented for computing Base64 Md5 checksum for given content
   
* /service
   * LinkService -> is Callable class to get content from given link.
   
* /fileProcess
   * FileProcesses -> has methods to
   
                    - get file path from user
                    
                    - read file content
                    
                    - find links in file
                    
                    - check validation of links
                    
                    - get content of links
                    
                    - print links
                    
              
   
                  



