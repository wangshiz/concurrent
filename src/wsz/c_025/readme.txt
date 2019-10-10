总结
1.对于map/set的选择使用
不加锁
Hashmap
Treemap
Linkedhashmap


想加锁
Hashtable
Connections.synchronizedxxx


并发性较高
Concurrenthashmap
Concurrentskiplistmap 可排序


2.队列
ArrayList
LinkedList
Collections.synchronizedXXX
Vector
CopyOnWriteList
Queue
    ConcurrentLinkedQueue //ConcurrentArrayQueue
    BlockingQueue
        LinkedBlockingQueue
        ArrayBlockingQueue
        TransferQueue
        SynchronusQueue
    DelayQueue执行定时任务
