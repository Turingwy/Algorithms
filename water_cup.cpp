/* 倒水问题 */

#include <iostream>

using namespace std;

#define CNT 1000

typedef struct state
{
    int water[3];
    int pre;
} state;

int cnts[3];

int last_index;
state q[CNT*CNT];

int vis[CNT][CNT];

int front, rear;
int bfs()
{
    while(front < rear)
    {
        state *cur = &q[front++];
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                if(i != j && cur->water[i] != 0 && cur->water[j] != cnts[j]) 
                {
                    state *next = &q[rear++];
                    int m = cnts[j] - cur->water[j] > cur->water[i] ? cur->water[i] : cnts[j] - cur->water[j];
                    next->water[i] = cur->water[i] - m;
                    next->water[j] = cur->water[j] + m;
                    next->water[3-i-j] = cur->water[3-i-j];
                    next->pre = front-1;
                    if(vis[next->water[1]][next->water[2]])
                    {
                        rear--;
                        continue;
                    }
                    last_index = rear-1;
                    for(int k = 0; k < 3; k++)
                    {
                        if(next->water[k] == 4) {
                            return 1;
                        }
                    }
                }
            }
        }
    }

    return 0;
}

int main()
{
    cnts[0] = 6;
    cnts[1] = 3;
    cnts[2] = 1;
    q[rear++].water[0] = 6;
    if(bfs())
    {
        while(last_index != 0)
        {
        for(int i = 0; i < 3; i++)
        {
            std::cout << q[last_index].water[i] << " ";
            }
            std::cout << std::endl;

            last_index = q[last_index].pre;
        }
    }

}
