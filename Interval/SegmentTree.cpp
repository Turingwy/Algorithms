#include <vector>
#include <iostream>

struct Node {
    int left, right;
    int max, min, sum;
    int add;
    Node(int left, int right) :left(left), right(right) {
        max = min = sum = 0;
        add = 0;
    }

    Node() = default;
};

int value[] = {3, 4, 2, 1, 5, 6};

Node tree[4 * 6];

void createTree(int left, int right, int pos) {
    if (left > right)
        return;
    tree[pos].left = left;
    tree[pos].right = right;
    if (left == right) {
        tree[pos].max = tree[pos].min = tree[pos].sum = value[left];
        return;
    }

    int mid = (left + right) / 2;
    createTree(left, mid, 2*pos);
    createTree(mid + 1, right, 2*pos+1);

    tree[pos].max = std::max(tree[2*pos].max, tree[2*pos+1].max);
    tree[pos].min = std::min(tree[2*pos].min, tree[2*pos+1].min);
    tree[pos].sum = std::min(tree[2*pos].sum, tree[2*pos+1].sum);
}

std::vector<int> queryInterval(int left, int right, int pos) {
    if (left <= tree[pos].left && right >= tree[pos].right) {
        return {tree[pos].max, tree[pos].min, tree[pos].sum};
    }
    if (tree[pos].add != 0) {
        if (tree[2*pos].left != tree[2*pos].right)
            tree[2*pos].add += tree[pos].add;
        if (tree[2*pos+1].left != tree[2*pos+1].right)
            tree[2*pos+1].add += tree[pos].add;
        tree[2*pos].max += tree[pos].add;
        tree[2*pos+1].max += tree[pos].add;
        tree[2*pos].min += tree[pos].add;
        tree[2*pos+1].min += tree[pos].add;
        tree[2*pos].sum += tree[pos].add * (tree[2*pos].right - tree[2*pos].left + 1);
        tree[2*pos+1].sum += tree[pos].add * (tree[2*pos+1].right - tree[2*pos+1].left + 1);
        tree[pos].add = 0;
    }

    int mid = (tree[pos].left + tree[pos].right) / 2;
    if (left > mid) {
        return queryInterval(left, right, 2*pos+1);
    }
    else if (right <= mid){
        return queryInterval(left, right, 2*pos);
    }
    else {
        auto lresult = queryInterval(left, right, 2*pos);
        auto rresult = queryInterval(left, right, 2*pos+1);
        return {std::max(lresult[0], rresult[0]), std::min(lresult[1], rresult[1]), lresult[2] + rresult[2]};
    }
}

void addInterval(int left, int right, int pos, int value) {
    if (left <= tree[pos].left && right >= tree[pos].right) {
        if (left != right)
            tree[pos].add += value;
        tree[pos].max += value;
        tree[pos].min += value;
        tree[pos].sum += value * (tree[pos].right - tree[pos].left + 1);
        return;
    }

    int mid = (tree[pos].left + tree[pos].right) / 2;
    if (left > mid) {
        addInterval(left, right, 2*pos+1, value);
    }
    else if (right <= mid) {
        addInterval(left, right, 2*pos, value);
    }
    else {
        addInterval(left, right, 2*pos, value);
        addInterval(left, right, 2*pos+1, value);
    }
    tree[pos].max = std::max(tree[2*pos].max, tree[2*pos+1].max);
    tree[pos].min = std::min(tree[2*pos].min, tree[2*pos+1].min);
    tree[pos].sum = tree[2*pos].sum + tree[2*pos+1].sum;
}


int main() {
    createTree(0, 5, 1);
    addInterval(5, 5, 1, 4);
    addInterval(4, 5, 1, 4);
    addInterval(0, 4, 1, 10);
    addInterval(5, 5, 1, 4);
    addInterval(5, 5, 1, 4);
    addInterval(5, 5, 1, 4);
for (int i = 0; i < 4*6; i++) {
        std::cout << tree[i].left << " " << tree[i].right << " " <<  tree[i].add << std::endl;
    }
    std::cout << queryInterval(0, 5, 1)[0] << std::endl;

}
