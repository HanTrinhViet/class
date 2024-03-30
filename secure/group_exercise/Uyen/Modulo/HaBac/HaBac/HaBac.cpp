#include<iostream>
#include<fstream>
#include<math.h>
#define ll long long
using namespace std;
ll haBac(ll a, ll m, ll n) {
	if (m == 1) return a % n;
	if (m % 2 == 0) {
		return (haBac(a, int(m / 2), n) * haBac(a, int(m / 2), n)) % n;
	}
	else {
		return (haBac(a, int(m / 2), n) * haBac(a, int(m / 2), n) * haBac(a, 1, n)) % n;
	}
}

int main() {
	int a, m, n;
	ifstream in("habac.txt");
	in >> a;
	in >> m;
	in >> n;
	cout << "\n=> " << a << "^" << m << " mod " << n << " = " << haBac(a, m, n);
	
	return 0;
}
