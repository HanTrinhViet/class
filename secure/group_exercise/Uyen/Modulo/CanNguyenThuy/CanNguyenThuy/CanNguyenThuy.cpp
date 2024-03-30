#include<iostream>
#include<algorithm>
#include<fstream>
#include<math.h>

using namespace std;

int GCD(int u, int v) {
	while (v != 0) {
		int r = u % v;
		u = v;
		v = r;
	}
	return u;
}

unsigned int cal_phi_n(int n) {
	unsigned int result = 0;
	for (int i = 0; i < n; i++)
		if (GCD(i, n) == 1)
			result++;
	return result;
}



int main() {
	int n, a;
	ifstream in("cnt.txt");
	in >> a;
	in >> n;


	if (GCD(n, a) == 1) {
		int phi_n = cal_phi_n(n);
		bool flag = false;
		if (int(pow(a, phi_n)) % n == 1) flag = true;

		for (int i = 1; i < phi_n; i++) {
			if (int(pow(a, i)) % n == 1) {
				flag = false;
				break;
			}
		}

		if (flag == true) cout << a << " la can nguyen thuy cua " << n;
		else cout << a << " khong la can nguyen thuy cua " << n;
	}
	else cout << a << " khong la can nguyen thuy cua " << n;

	return 0;
}
