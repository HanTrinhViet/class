#include<iostream>
#include<fstream>
using namespace std;
int main() {
	int a, b, n;
	cout << "-------------------------log(a,b) mod n-------------------------" << endl;
	ifstream in("logarit.txt");
	in >> a;
	in >> b;
	in >> n;
	bool fag = false;
	for (int i = 1; i < n; i++) {
		if (((int)pow(a, i) % n) == b) {
			cout << "log(" << a << "," << b << ") mod " << n << " = " << i;
			fag = true;
		}
	}
	if (!fag)cout << "Khong co";
}