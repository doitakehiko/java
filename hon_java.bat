SET $OutputEncoding = [Console]::OutputEncoding
find " " D:\doc\manga?.txt | call java MoveTailToHead | call sort > D:\doc\manga_res.txt
find " " D:\doc\lightnovel?.txt |  call java MoveTailToHead | call sort > D:\doc\lightnovel_res.txt
