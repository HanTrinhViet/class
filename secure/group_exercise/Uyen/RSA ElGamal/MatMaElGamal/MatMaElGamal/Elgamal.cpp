#include <iostream>
#include <fstream>
#include <string>
#include <math.h>
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

ll nghichdao(ll a, ll n) {
	ll Q, A1 = 1, A2 = 0, A3 = n, B1 = 0, B2 = 1, B3 = a;
	while (B3 != 0 && B3 != 1) {
		Q = A3 / B3;
		ll B1_cp = B1, B2_cp = B2, B3_cp = B3;
		B1 = A1 - B1 * Q;
		B2 = A2 - B2 * Q;
		B3 = A3 - B3 * Q;

		A1 = B1_cp; A2 = B2_cp; A3 = B3_cp;
	}
	return (B2 + n) % n;
}

int main() {
	ll q, a, xA, k, M;
	ifstream in("elgamal.txt");
	in >> q;
	in >> a;
	in >> xA;
	int yA = haBac(a, xA, q);
	cout << "a) Khoa cong khai cua An PU={ " << q << ", " << a << ", " << yA << "};" << endl;
	in >> k;
	in >> M;
	int K = haBac(yA, k, q);
	int C1 = haBac(a, k, q);
	int C2 = (K * M) % q;
	cout << "b) Ban ma la: (C1;C2) = (" << C1 << "; " << C2 << ");";
	int KA = haBac(C1, xA, q);
	int MA = ((C2 % q) * nghichdao(KA, q)) % q;
	cout << endl << "c) K = " << KA << "; M = " << MA;
}


