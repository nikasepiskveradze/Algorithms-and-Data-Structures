#include <iostream>
#include <vector>

using namespace std;

class SegmentTree{
private:
	vector<int> segmentTree;
	vector<int> lazyTree;

	int leftChild(int p){
		return (p << 1) + 1;
	}

	int rightChild(int p){
		return (p << 1) + 2;
	}

	int Min(int a, int b){
		return a > b ? b : a;
	}

	void constructMinSegmentTree(vector<int> &segmentTree, vector<int> inputTree, int low, int high, int pos){
		if (low == high){
			segmentTree[pos] = inputTree[low];
			return;
		}
		int middle = (low + high) / 2;
		constructMinSegmentTree(segmentTree, inputTree, low, middle, leftChild(pos));
		constructMinSegmentTree(segmentTree, inputTree, middle + 1, high, rightChild(pos));
		segmentTree[pos] = Min(segmentTree[leftChild(pos)], segmentTree[rightChild(pos)]);
	}

public:
	SegmentTree(vector<int> tree){

		for (int i = 0; i < 4*tree.size(); i++){
			segmentTree.push_back(INT_MAX);
			lazyTree.push_back(0);
		}

		constructMinSegmentTree(segmentTree, tree, 0, tree.size() - 1, 0);
	}

	int rangeMinimumQuery(vector<int> segmentTree, int qlow, int qhigh, int low, int high, int pos){
		if (qlow <= low && qhigh >= high){
			return segmentTree[pos];
		}

		if (qlow > high || qhigh < low){
			return INT_MAX;
		}

		int middle = (low + high) / 2;

		return Min(rangeMinimumQuery(segmentTree, qlow, qhigh, low, middle, leftChild(pos)),
			rangeMinimumQuery(segmentTree, qlow, qhigh, middle + 1, high, rightChild(pos)));
	}

	int rangeMinimumQueryLazy(vector<int> segmentTree, vector<int> lazyTree, int qlow, int qhigh, int low, int high, int pos){
		if (low > high){
			return INT_MAX;
		}

		if (lazyTree[pos] != 0){
			segmentTree[pos] += lazyTree[pos];
			if (low != high){
				lazyTree[leftChild(pos)] += lazyTree[pos];
				lazyTree[rightChild(pos)] += lazyTree[pos];
			}
			lazyTree[pos] = 0;
		}


		if (qlow <= low && qhigh >= high){
			return segmentTree[pos];
		}

		if (qlow > high || qhigh < low){
			return INT_MAX;
		}

		int middle = (low + high) / 2;

		return Min(rangeMinimumQuery(segmentTree, qlow, qhigh, low, middle, leftChild(pos)),
			rangeMinimumQuery(segmentTree, qlow, qhigh, middle + 1, high, rightChild(pos)));
	}

	void updateSegmentTreeRangeLazy(vector<int> &segmentTree, vector<int> &lazyTree, int startRange, int endRange, int delta, int low, int high, int pos){
		if (low > high){
			return;
		}

		if (lazyTree[pos] != 0){
			segmentTree[pos] += lazyTree[pos];
			if (low != high){
				lazyTree[leftChild(pos)] += lazyTree[pos];
				lazyTree[rightChild(pos)] += lazyTree[pos];
			}
			lazyTree[pos] = 0;
		}

		if (startRange > high || endRange < low){
			return;
		}

		if (startRange <= low && endRange >= high){
			segmentTree[pos] += delta;
			if (low != high){
				lazyTree[leftChild(pos)] += delta;
				lazyTree[rightChild(pos)] += delta;
			}
			return;
		}

		int mid = (low + high) / 2;
		updateSegmentTreeRangeLazy(segmentTree, lazyTree, startRange, endRange, delta, low, mid, leftChild(pos));
		updateSegmentTreeRangeLazy(segmentTree, lazyTree, startRange, endRange, delta, mid + 1, high, rightChild(pos));

		segmentTree[pos] = Min(segmentTree[leftChild(pos)], segmentTree[rightChild(pos)]);

	}

	vector<int> getSegmentTree(){
		return segmentTree;
	}

	vector<int> getLazyTree(){
		return lazyTree;
	}

};


int main(){

	vector<int> vec = { -1, 2, 4, 0 };

	SegmentTree obj(vec);

	vector<int> segmentTree = obj.getSegmentTree();
	vector<int> lazyTree = obj.getLazyTree();
	
	int min = obj.rangeMinimumQueryLazy(segmentTree, lazyTree, 1, 3, 0, vec.size() - 1, 0);

	cout << min << endl;

	return 0;
}