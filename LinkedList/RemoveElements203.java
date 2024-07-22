package LinkedList;

 class RemoveElements203 {
    public ListNode removeElements(ListNode head, int val){
        ListNode dummy = new ListNode(0);
        dummy.next = head; 
        ListNode prev = dummy, current = head; 
        while (current != null){
            if (current.val == val){
                prev.next = current.next;
            } else {
                prev = current;
            }
            current = current.next; 
        }
        return dummy.next; 
    }
}
