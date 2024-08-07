#include <iostream>
#include <string>
#include <conio.h>
using namespace std;

class card
{
    int choice, cond, mul, a, rem, i, restart;
    string num;
    int sumodd = 0, sumeven = 0, len = 0, result = 1;

public:
    // function for checking user do not enter alphabet....
    bool check_input()
    {
        for (i = 0; i < len; i++)
        {
            if (num[i] > 57)
            {
                if (i == len - 1)
                {
                    break;
                }
                return false;
            }
        }
        return true;
    }
    // function for checking type of card....
    void check_card_type()
    {
        int first_num = (num[0] - 48);
        switch (first_num)
        {
        case 4:
            cout << "Your card type is 'VISA'" << endl
                 << endl;
            break;
        case 5:
            cout << "Your card type is 'MASTERCARD CARDS'" << endl
                 << endl;
            break;
        case 3:
            cout << "Your card type is 'AMERICAN EXPRESS CARDS'" << endl
                 << endl;
            break;
        case 6:
            cout << "Your card type is 'DISCOVER CARDS'" << endl
                 << endl;
            break;
        case 7:
            cout << "Your card type is 'AMERICAN EXPRESS CARDS'" << endl
                 << endl;
            break;
        default:
            cout << "Sorry this type is not aviable in data" << endl
                 << endl;

            break;
        }
    } // function is end...
    // function for checking card number is valid or not....
    void check_card_validity()
    {
        len = num.size();
        for (i = len - 1; i >= 0; i--)
        {
            sumodd = sumodd + num[i] - 48;
            if (i == 0 && (len % 2 == 1))
            {
                break;
            }
            --i;
            mul = (num[i] - 48) * 2;
            rem = mul % 10;
            if (mul > 9)
            {
                a = 1;
            }
            else
            {
                a = 0;
            }
            sumeven = sumeven + rem + a;
        }
        int sum1 = (sumeven + sumodd) % 10;
        if (sum1 == 0)
        {
            cout << "Your card number is valid..." << endl
                 << endl;
        }
        else
        {
            cout << "Your card number is not valid..." << endl
                 << endl;
        }
    }
    // function for which work user want....
    void choi()
    {
        //  3rd  loop start  //for restart of program
        do
        { // using do..while 2nd loop is start, for user enter alphabet in card number....
            cout << "Enter your card number..." << endl;
            getline(cin, num);
            do
            {
                cout << endl;
                if (check_input())
                {
                    check_card_validity();
                    check_card_type();
                    cond = 0;
                }
                else
                {
                    cout << "Do not enter alphabet" << endl
                         << endl
                         << endl
                         << endl;
                    cond = 1;
                }
            } while (cond); // 2nd  loop is end....

            cout << "Do you want to run again this program..." << endl;
            cout << "Press any number key for start again..." << endl;
            cout << "Press '0' for exit..." << endl;
            cin >> restart;
            if (0 == restart)
            {
                cond = 0;
            }
            else
            {
                cond = 1;
            }
        } while (cond); // 3rd  loop is end
    }                   // function is endhdd...

}; // class is end;
//  main function is started....
int main()
{
    system("CLS");
    cout << "WElcome to our page..." << endl;
    card c1;
    c1.choi();
    getch();
    return 0;
}