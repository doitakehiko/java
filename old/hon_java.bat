SET $OutputEncoding = [Console]::OutputEncoding
type D:\doc\manga?.txt | find " " |  call java MoveTailToHead | call sort > D:\doc\manga_res.txt
type D:\doc\lightnovel?.txt  | find " "  | call java MoveTailToHead | call sort > D:\doc\lightnovel_res.txt
