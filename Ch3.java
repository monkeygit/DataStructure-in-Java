/*
 *3.11.e
 */
public boolean removeIfContains(T x){
	Node<T> p = beginMarker.next;
	Node<T> q = beginMarker;
	while(p != null){
		if(p.data == x){
			if(p.next != null)
				q.next = p.next;
			else
				q.next = null;
			return true;
		}
		q = p;
		p = p.next;
	}
	return false;
}

/*
 *3.12.d
 */
public 