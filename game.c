#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>
#include <string.h>
int read (char mode[10])
{
  // declare a pointer called fp of the type file
  FILE *fp;
  int number;
  // turn fp into a pointer to file.txt in read binary mode
  fp = fopen (strcat (mode, ".txt"), "rb");
// fp will be 0 ie true if the file exists
  while (!feof (fp))
    {  
      fscanf (fp, "%d", &number);      
    }
    // close the file
    fclose (fp);
  return number;
}

int write(int value, char mode[10])
{
  FILE *fp;
  char str[10];
  fp = fopen (mode, "wb");
  sprintf(str, "%d", value);
  fprintf (fp, str);
  fclose (fp);
}

int main(void) 
{
  int random_number;
  int guess;
  int guesses=0;
  char strmode[10];
  int mode;
  int best_guess;
  printf("Mode 1: 0-50\nMode 2: 0-100\nMode 3: 0-250\nMode 4: 0-500\nMode 5 0-2000\n1-5?");
  scanf("%d",&mode);
  sprintf(strmode, "%d", mode);
  best_guess=read(strmode);
srand(time(NULL));

  switch(mode)
  {
    case 1: random_number=round(rand()/(RAND_MAX / 50));
    break;
    case 2: random_number=round(rand()/(RAND_MAX / 100));
    break;
    case 3: random_number=round(rand()/(RAND_MAX / 250));
    break;
    case 4: random_number=round(rand()/(RAND_MAX / 500));
    break;
    case 5: random_number=round(rand()/(RAND_MAX / 2000));
    break;
  }
  while (guess!=random_number)
  {
    printf("Please enter your guess ");
    scanf ("%d", &guess);
    if (guess>random_number)
    {
      printf("Too high\n");
    }
    else if (guess<random_number)
    {
      printf("Too low\n");
    }
    guesses++;
  }
  printf("It took you %d guesses to get the correct answer of %d\n",guesses,random_number);
  if (best_guess!=100)
  {
if (guesses<best_guess)
  {
    printf("Well done, you beat the best score of %d\n",best_guess);
    write(guesses,strmode);
  }
  else if (guesses==best_guess)
  {
    printf("Well done, you equaled the best score of %d\n",best_guess);
  }
  else
  {
    printf("You didn't beat the best score of %d\n",best_guess);
  }
  }
  else 
  {
    write(guesses,strmode);
  }
  
  return 0;
}
