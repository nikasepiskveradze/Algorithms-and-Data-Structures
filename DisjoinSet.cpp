class DisjointSet {
private:
	vector<int> Parent;
	vector<int> Size;
public:

	DisjointSet(int n){
		for (int i = 0; i < n; i++){
			Parent.push_back(i);
			Size.push_back(1);
		}
	}

	int findRoot(int element){
		if (Parent[element] == element){
			return element;
		}
		else {
			int root = findRoot(Parent[element]);
			Parent[element] = root;

			return root;
		}
	}

	bool isSameSet(int e1, int e2){
		return findRoot(e1) == findRoot(e2);
	}

	void unionSet(int e1, int e2){
		if (!isSameSet(e1, e2)){
			int p1 = findRoot(e1);
			int p2 = findRoot(e2);

			if (Size[p1] > Size[p2]){
				Parent[p2] = p1;
				Size[p1] += Size[p2];
			}
			else {
				Parent[p1] = p2;
				Size[p2] += Size[p1];
			}
		}
	}

	int sizeOfUnion(int n){
		int k = findRoot(n);
		return Size[k];
	}

};