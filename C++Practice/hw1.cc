#include <iostream>
using namespace std;
class BinaryNumber {
	private:
	       	int data[];
		bool overflow;
		bool validate();
		bool validate(string str);
	public:
		BinaryNumber(int length);
		BinaryNumber(string str);
		int getLength();
		int getDigit(int index);
		void shiftR(int amount);
		void add(BinaryNumber x);
		string toString();
};
BinaryNumber::validate(){
	try {int x = data[0]; return true;}
	catch (exception e){ return false;}
}
BinaryNumber::validate(string str){
	bool check = true;
	for(int i = 0;i < str.length();i++){
		if(isdigit(str[i]) && (str[i] == 0 || str[i] == 1))
		       check  = false;	
	}
	return check;
}
BinaryNumber::BinaryNumber(int length){
	if(length>0) 
		 int data[length];
	else cout << "Bad Length size\n";
}
BinaryNumber::BinaryNumber(string str){
	if(!validate(str)){
		cout<<"Not a binary Number\n";
		return;
	}
	int arr[str.length()];
	for (int i = 0; i < str.length();i++){
		char num = str[i];
		arr[i]=num-'0';
	}
	data = arr;
}
BinaryNumber::getLength(){
}
BinaryNumber::getDigit(int index){
}
BinaryNumber::shiftR(int amount){
}
BinaryNumber::add(BinaryNumber x){
}

int main(){

}
