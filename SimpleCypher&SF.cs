using System.Collections.Generic;
using System.Text;
double[] ruchances = new double[] { 
    0.175, 0.062, 0.014, 0.038, 0.013, 0.025, 0.072, 0.007, 0.035,
    0.062, 0.012, 0.028, 0.035, 0.026, 0.053, 0.090, 0.023, 
    0.040, 0.045, 0.053, 0.021, 0.002, 0.009, 0.003, 0.013, 
    0.006, 0.003, 0.014, 0.038, 0.014, 0.003, 0.006, 0.040
};
Console.WriteLine("Hello, World!");


List<char> letters = new List<char>();
List<char> ruletters = new List<char>(); 

double[] counts = new double[33];
string corestr = "YF2 3TCT1 IYB TV VY 6FYYB 32G6FT3B6 6 O3Y ZTZHB16 ZCLOTTNCLV6BYI4VHN TC5LVTO ZTSFLB4 Y5T VL XYFI6UT T1LVX6OLEB31 1CLNTF";
letters.Add(' ');

for (int i = 0; i < 26; i++)
{
    char l = (char)('A' + i);
    letters.Add(l);
}
letters.Add('1');
letters.Add('2');
letters.Add('3');
letters.Add('4');
letters.Add('5');
letters.Add('6');


for (int i = 0; i < 33; i++)
{
    counts[i]= 0;
}

ruletters.Add(' ');
for (int i = 0; i < 33; i++)
{
    char l = (char)('А' + i);
    ruletters.Add(l);
}



int ind;

for (int i = 0; i < corestr.Length; i++)
{
    ind = letters.IndexOf(corestr[i]);
    counts[ind] = counts[ind] + 1;
}
/*for (int j = 0; j < letters.Count; j++)
{
    //Console.WriteLine(ruletters[j] + ": " + ruchances[j] + "    " + letters[j] + ": " + counts[j] / corestr.Length);

}*/
//Sorting
Console.WriteLine("======================");
double bubd = 0;
char bubc;
bool needIter = true;
while (needIter)
{
    needIter = false;
    for(int i = 1;i < letters.Count; i++)
    {
        if (counts[i] > counts[i - 1])
        {
            needIter = true;
            bubd = counts[i];
            counts[i] = counts[i-1];
            counts[i-1] = bubd;

            bubc = letters[i];
            letters[i] = letters[i-1];
            letters[i-1] = bubc;
        }
        if (ruchances[i]> ruchances[i - 1])
        {
            needIter=true;
            bubd = ruchances[i];
            ruchances[i] = ruchances[i-1];
            ruchances[i-1]  = bubd;

            bubc = ruletters[i];
            ruletters[i] = ruletters[i-1];
            ruletters[i - 1] = bubc;
        }
    }
}
for (int j = 0; j < letters.Count; j++)
{
    Console.WriteLine(ruletters[j] + ": " + ruchances[j] + "    " + letters[j] + ": " + counts[j] / corestr.Length);

}

/*Shennone-Fano
Console.WriteLine("==========");
string rustr = "ПОЭЗИИ ЖИВОЙ И ЯСНОЙ, ВЫСОКИХ ДУМ И ПРОСТОТЫ";
ruletters.Add(',');
counts = new double[35];
for (int i = 0; i < 33; i++)
{
    counts[i] = 0;
}
for (int i = 0; i < rustr.Length; i++)
{
    ind = ruletters.IndexOf(rustr[i]);
    counts[ind] = counts[ind] + 1;
}

while (needIter)
{
    needIter = false;
    for (int i = 1; i < ruletters.Count; i++)
    {
        if (counts[i] > counts[i - 1])
        {
            needIter = true;
            bubd = counts[i];
            counts[i] = counts[i - 1];
            counts[i - 1] = bubd;

            bubc = ruletters[i];
            ruletters[i] = ruletters[i - 1];
            ruletters[i - 1] = bubc;
        }

    }
}
for (int j = 0; j < ruletters.Count; j++)
{
    Console.WriteLine(ruletters[j] + ": " +  counts[j] / rustr.Length);

}

*/
