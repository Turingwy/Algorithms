#include <iostream>
#include <vector>

struct TreeNode {
    TreeNode *fc, *nb;
    int id;
    int priority;
    TreeNode() : fc(NULL), nb(NULL) { }
    TreeNode(int id, int priority) : fc(NULL), nb(NULL), id(id), priority(priority) { }
};

class PairingForest {
    TreeNode *forest;
    std::vector<TreeNode *> xref;

public:
    PairingForest() : forest(NULL) { }

    int insert(int priority) {
        TreeNode *newTree = new TreeNode(xref.size(), priority);
        xref.push_back(newTree);
        newTree->nb = forest;
        forest = newTree;
        return xref.size()-1;
    }

    int getMin() {
        while ((forest = pairForest(forest))->nb != NULL)
            ;

        return forest->priority;
    }

    void deleteMin() {
        getMin();
        TreeNode *waitDelete = forest;
        forest = forest->fc;
        xref[waitDelete->id] = NULL;
        delete waitDelete;
    }

    void decreaseKey(int id, int priority) {
        TreeNode *waitDecrease = xref[id];
        TreeNode *newTree = new TreeNode(id, priority);
        newTree->fc = waitDecrease->fc;
        waitDecrease->id = -1;
        xref[id] = newTree;
        newTree->nb = forest;
        forest = newTree;
    }

    TreeNode *pairTree(TreeNode *t1, TreeNode *t2) {
        std::cout << "--------------" << std::endl;
        std::cout << t1->id << std::endl;
        std::cout << t2->id << std::endl;
        std::cout << "--------------" << std::endl;
        if (t1->id == -1 && t2->id == -1) {
            delete  t1;
            delete  t2;
            return NULL;
        }
        if (t1->id == -1) {
            delete t1;
            return t2;
        }
        if (t2->id == -1) {
            delete t2;
            return t1;
        }
        if (t1->priority < t2->priority) {
            t2->nb = t1->fc;
            t1->fc = t2;
            return t1;
        }
        else {
            t1->nb = t2->fc;
            t2->fc = t1;
            return t2;
        }
    }

    TreeNode *pairForest(TreeNode *forest) {
        if (forest == NULL)
            return NULL;

        TreeNode *newForest = NULL;
        TreeNode *t1 = forest;
        while (t1 != NULL) {
            TreeNode *t2 = t1->nb;
            t1->nb = NULL;

            if (t2 == NULL) {
                t1->nb = newForest;
                newForest = t1;
                break;
            }
            else {
                TreeNode *nextTree = t2->nb;
                t2->nb = NULL;
                TreeNode *t3 = pairTree(t1, t2);
                if (t3 != NULL) {
                    t3->nb = newForest;
                    newForest = t3;
                }
                t1 = nextTree;
            }
        }
        return newForest;

    }

};


int main() {
    PairingForest pf;
    for (int k = 0; k < 10; ++k) {
        pf.insert(k);
    }
    pf.decreaseKey(8, -200);
    std::cout << pf.getMin() << std::endl;
    pf.decreaseKey(3, -10);
    pf.decreaseKey(5, -10);
    pf.deleteMin();
    pf.deleteMin();
    pf.deleteMin();
    pf.deleteMin();
    std::cout << pf.getMin() << std::endl;
}

