

using System.Text;

List<char> letters = ['Р', 'В', 'А', 'П', 'О', '#'];
double[] counts = new double[6];
string corestr = "РОПВПАРВВВ#";
Console.WriteLine($"Encoding: {corestr}");
int ind;
for (int i = 0; i < corestr.Length; i++)
{
    ind = letters.IndexOf(corestr[i]);
    counts[ind] = counts[ind] + 1;
}
for(int i = 0; i <counts.Length; i++)
{
    counts[i] = counts[i]/corestr.Length;
}

//Sorting
bool needIter = true;
double bubd = 0;
char bubc;
while (needIter)
{
    needIter = false;
    for (int i = 1; i < letters.Count; i++)
    {
        if (counts[i] > counts[i - 1])
        {
            needIter = true;
            bubd = counts[i];
            counts[i] = counts[i - 1];
            counts[i - 1] = bubd;

            bubc = letters[i];
            letters[i] = letters[i - 1];
            letters[i - 1] = bubc;
        }
    }
}

for (int j = 0; j < letters.Count; j++)
{
    Console.WriteLine(  letters[j] + ": " + counts[j] );

}


double cmin = 0;
double cmax = 1;
double delta;
//Console.WriteLine(getStart('П') + "  " + getEnd('П'));
for (int i = 0;i < corestr.Length; i++)
{
    char c = corestr[i];
    delta = cmax - cmin;
    cmax = cmin+delta*getEnd(c);
    cmin = cmin + delta*getStart(c);
}
Console.WriteLine("[ "+cmin + " ; " + cmax+']');

Random rnd = new Random();
double N = rnd.NextDouble()*(cmax - cmin)+cmin;

Console.WriteLine("\nDecoding...");
StringBuilder sb = new StringBuilder();
delta = 0;
while (true)
{
    char c = findInterval(N);
    if (c == '#') {  break; }
    sb.Append(c);
    delta = getEnd(c)-getStart(c);
    N = (N-getStart(c))/delta;

}
Console.WriteLine(sb.ToString());

double getStart(char c)
{
    int ind = letters.IndexOf(c);
    if (ind == 0) return 0;
    double a=0;
    for (int i = 0; i < ind; i++)
    {
        a += counts[i];
    }
    return a;
} 

double getEnd(char c)
{
    int ind = letters.IndexOf(c);

    double a = 0;
    for (int i = 0; i <= ind; i++)
    {
        a += counts[i];
    }
    return a;
}
char findInterval(double v)
{
    int ind=0;
    double a=0;
    for (int i = 0; i < counts.Length; i++)
    {
             
        if (v>a && v<= a + counts[i]) { ind = i; break; }
        a += counts[i];
    }
    return letters[ind];    
    
}