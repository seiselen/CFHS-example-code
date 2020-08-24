//>>> OBJECT DECLARATIONS
CollideTest collideTester;
ScoreKeeper myScoreKeeper;

//>>> GLOBAL VAR DEC'S / INIT'S
color mouseIsOverCol = color(0,255,0);
color mouseNoOverCol = color(0,60,0);
color scoreCol       = color(255);


/*----------------------------------------------------------------------
|>>> Processing Function setup: Use for initialization
+---------------------------------------------------------------------*/
void setup(){
  
  size(512,512);
  textSize(28);
  
  collideTester = new CollideTest(width/2,height/2,64);
  myScoreKeeper = new ScoreKeeper();
  
} // Ends Processing Function setup


/*----------------------------------------------------------------------
|>>> Processing Function draw: Use for interaction and animation. Note
|    that I've labelled the '3 Main Parts of [Game] Loop'
+---------------------------------------------------------------------*/
void draw(){
  //>>> UI-UX IN-FRAME CALLS
  
  //>>> LOGIC CALLS
  collideTester.update();
  myScoreKeeper.update(collideTester);
    
  //>>> RENDER CALLS
  background(60);  
  collideTester.render(); 
  myScoreKeeper.render();
} // Ends Processing Function draw


/*----------------------------------------------------------------------
|>>> ScoreKeeper Class: Used to keep score (# collisions) and display
+---------------------------------------------------------------------*/
class ScoreKeeper{
  private int currentScore = 0;
  
  // Wait a sec...Where's the constructor?!?

  public void update(CollideTest item){
    // How else can I write this conditional i.e. can we condense it?
    if(item.mouseIsOnMe()==true){currentScore++;}
  } // Ends Function update
  
  public void render(){
    fill(scoreCol);
    text("Current Score is "+currentScore+" collisions!",16,32);
  } // Ends Function render
  
} // Ends Class ScoreKeeper


/*----------------------------------------------------------------------
|>>> CollideTest Class: Used to show basic demo of collision detection
+---------------------------------------------------------------------*/
class CollideTest{
  private int     xPos; 
  private int     yPos; 
  private int     size;
  private float   sizeHalf;
  private boolean mouseIsOnMe = false;
     
  public CollideTest(int x, int y, int s){
    xPos = x; 
    yPos = y;
    size = s;
    sizeHalf = size/2;
  } // Ends Constructor
  
  public void update(){
    checkCollision();
  } // Ends Function update
  
  public void checkCollision(){
    if(abs(mouseX-xPos)<sizeHalf && abs(mouseY-yPos)<sizeHalf){mouseIsOnMe = true;}
    else{mouseIsOnMe = false;} 
  } // Ends Function checkCollision
  
  public boolean mouseIsOnMe(){
    return mouseIsOnMe;
  } // Ends Function getMouseIsOnMe

  public void render(){   
    noStroke();   
    if(mouseIsOnMe){fill(mouseIsOverCol);}
    else{fill(mouseNoOverCol);}
    ellipse(xPos,yPos,size,size);
  } // Ends Function render

} // Ends Class CollideTest
