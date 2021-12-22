color bgColor = color(31, 0, 48);

int windowX = 500;
int windowY = 500;
int pixelCount = windowX * windowY;

int sunCenterX = 250;
int sunCenterY = 250;

int sunDiameter = 300;
int sunRadius = sunDiameter/2;

int sunTopY = sunCenterY - sunDiameter/2;
int sunBottomY = sunCenterY + sunDiameter/2;

float x, y, w, h;

ArrayList<Rectangle> sections = new ArrayList<Rectangle>();

Rectangle one, two, three, four, five;

// RGB colors
color[] sunColors = {
  color(212, 202, 11), 
  color(214, 198, 30), 
  color(211, 170, 26), 
  color(216, 157, 51), 
  color(217, 124, 64), 
  color(213, 104, 81), 
  color(212, 51, 98), 
  color(215, 29, 121), 
  color(217, 11, 139), 
  color(217, 0, 151)
};

void setup() {
  // 1. Set the size of your sketch
  size(500, 500);

  x = sunCenterX - sunRadius;
  y = sunBottomY;
  w = 2 * sunDiameter;
  h = 40;

  one = new Rectangle(x, y, w, h);
  two = new Rectangle(x, y-45, w, h);
  three = new Rectangle(x, y-90, w, h);
  four = new Rectangle(x, y-135, w, h);
  five = new Rectangle(x, y-180, w, h);
  
  sections.add(one);
  sections.add(two);
  sections.add(three);
  sections.add(four);
  sections.add(five);
}


void draw() {
  // 2. Draw the bgColor background color
  background(bgColor);
  /*
   * PART 1: Drawing the sun
   */

  // Draw an ellipse for the sun in the center of the window
  // Use fill(sunColors[0]) to make it yellow
  // Use noStroke() to remove the black outline
  fill(sunColors[0]);
  ellipse(sunCenterX, sunCenterY, sunDiameter, sunDiameter);
  noStroke();
  // Do you see a yellow sun like in the 1st image?
  // If not, fix your code before proceeding.

  /*
   * PART 2: Drawing a color gradient on the sun
   *
   * This will make the sun have gradually different colors from the 
   * top to bottom
   */

  // Call the loadPixels() method to put all the pixel colors into
  // the pixels[] array
  // https://processing.org/reference/loadPixels_.html
  loadPixels();
  // Loop through all the pixels in your window.
  // By default, a pixel is a 1x1 colored square, so if the window width is 600 
  // and the height is 400 (600x400), then there are 600 * 400 = 240,000 pixels 

  // We want to change the color of our sun so use an if statement
  // to check if the pixel is the color of the yellow circle. 

  for (int i = 0; i < pixelCount; i++) {
    if (pixels[i] == sunColors[0]) {
      int y = i / width;
      float step = map(y, sunTopY, sunBottomY, 0, 1);
      pixels[i] = interpolateColor(sunColors, step);
    }
  }
  // If it's the same color we need to map the pixel to a
  // color in our sunColors[] array (see 2nd gradient image)

  // The top of the sun is yellow (sunColors[0]) and the bottom
  // of the sun is red (sunColors[sunColors.length - 1]

  // In order to get the right color, the y value from the top of
  // the sun to the bottom has to be mapped to a range from 0 to 1.
  // Use the map() function to do that:
  // int y = i / width;
  // float step = map(y, sunTopY, sunBottomY, 0, 1);

  // Call interpolateColor(sunColors, step) and save the color
  // variable that's returned

  // Set pixels[i] to the returned color 


  // Call updatePixels() after your loop through all the pixels to
  // update the pixel colors
  // https://processing.org/reference/updatePixels_.html
  updatePixels();

  /*
   * PART 3: Drawing the missing sections at the bottom of the sun
   *
   * The missing parts of the sun are created by drawing rectangles
   * over the sun with the same color as the background.
   */

  // Set the fill color to the background color
  fill(bgColor);
  // To draw each rectangle we need to. Find its x, y, width, height
  // *The y position can be any value within the sun:

  // *The height can be any value you choose:

  // *The x position can be the center of the sun's x position minus the radius:

  // * The width can be 2 times the radius

  // Do you see a section missing from the sun like in the 3rd image?
  for (int i = 0; i < sections.size(); i++) {
    rect(sections.get(i).x, sections.get(i).y, sections.get(i).w, sections.get(i).h);
    sections.get(i).y-=0.5;
    sections.get(i).h-=0.085;
    if (sections.get(i).y < width/3) {
      sections.get(i).y = sunBottomY;
      sections.get(i).h = 40;
    }
  }
  /*
   * PART 4: Moving the missing sun sections
   *
   * To move a section upwards each rectangle's y value needs to decrease.
   * To make the section get smaller, its height needs to also decrease.
   */

  // Decrease the y variable of the rectangular section created in PART 3.
  // If there isn't a variable, declare a float variable OUTSIDE of the
  // draw function AND initialize it in the setup() function.

  // Do you see the rectangle moving upwards?

  // Pick a y positon to be the location when the sections stop moving up.
  // If the rectangle's y positon is above this, move the rectangle's
  // y position back to the bottom of the sun.

  // Does the rectangle move back to the bottom?

  // Decrease the the height of the rectangle as it moves upwards.
  // Similar to the y positon, a float variable for the height needs to be
  // created if it doesn't already exist.

  // Adjust the amount to decrease so that it disappears close to the top.

  // Add code to reset the height of the rectangle when it moves back to
  // the bottom of the sun.

  /*
   * PART 5: Managing the missing sun sections
   *
   * Using a list to manage moving multiple missing sun sections 
   */

  // Figure out how to create the other missing sun sections using the
  // code you wrote for the 1 missing sun section.
  // *HINT* You can use the Rectangle class defined below to create
  //        a list of Rectangles.


  /*
   * PART 6: Adding extras
   *
   * If you want to make your retro sun look more unique, try adding
   * reflections and stars.
   * See the Retro Sun webpage on the League level 3 website
   * for example code and classes.
   */
}

// Placed here so it can be used by all classes
// Variable step should be between 0 and 1, inclusive
color interpolateColor(color[] arr, float step) {
  int sz = arr.length;

  if (sz == 1 || step <= 0.0) {
    return arr[0];
  } else if (step >= 1.0) {
    return arr[sz - 1];
  }

  float scl = step * (sz - 1);
  int i = int(scl);

  return lerpColor(arr[i], arr[i + 1], scl - i);
}

// Feel free to use this class to create a list of missing
// sections in the sun, for example:
// ArrayList<Rectangle> sections = new ArrayList<Rectangle>();
class Rectangle {
  float x, y, w, h;

  Rectangle(float x, float y, float w, float h) {
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
  }
}

class Star {
  int x;
  int y;
  color starColor;
  float startAlpha;
  float alpha;
  float diameter;

  Star(int x, int y, color col) {
    this.x = x;
    this.y = y;
    starColor = col;
    this.diameter = random(0.1, 3);
    this.startAlpha = random(1, 200);
    this.alpha = startAlpha;
  }
  
  void setAlpha(int alpha){
    this.alpha = constrain(alpha, startAlpha, 255);
  }

  void draw() {
    noStroke();
    fill(starColor, alpha);
    float blink = random(0, 0.8);
    ellipse(x, y, diameter + blink, diameter + blink);
  }
}

//class Reflection {
///*
//  // HSB colors
//  color[] barColors = {
//    color(285, 96.6, 23.1), 
//    color(312, 100, 42.7), 
//    color(340, 66.9, 60.4), 
//    color(11, 60.8, 62), 
//    color(340, 66.9, 60.4), 
//    color(312, 100, 42.7), 
//    color(285, 96.6, 23.1)
//  };
//*/
//  // RGB colors
//  color[] barColors = {
//    color(45, 2, 59), 
//    color(109, 0, 88), 
//    color(154, 51, 86), 
//    color(158, 79, 62), 
//    color(154, 51, 86), 
//    color(109, 0, 88), 
//    color(45, 2, 59)
//  };

//  int sunRadius;
//  int numReflectionBars;
//  int topX;
//  int topY;
//  int topWidth;
//  int bottomY;
//  int maxHeight;
//  float speed;
//  ArrayList lowerBars;
  
//  Reflection(int sunRadius, int numBars, int topX, int topY, float speed){
//    this.sunRadius = sunRadius;
//    this.topX = topX;
//    this.topY = topY;
//    this.speed = speed;

//    initialize(numBars);
//  }
  
//  void initialize(int numBars){
//    this.numReflectionBars = numBars;
    
//    topWidth = 2 * (sunRadius + sunRadius/3);
//    maxHeight = 10;
//    bottomY = topY + (numBars * 2 * maxHeight);
//    lowerBars = new ArrayList();
    
//    // Setup bottom relection bars
//    int x = topX;
//    int y = topY;
//    int w = topWidth;
//    int h = maxHeight;
//    for ( int i = 0; i < numReflectionBars; i++ ) {   
//      y += (bottomY - topY) / numBars;
//      x += sunRadius / 16;
//      w -= 2 * (sunRadius / 16);

//      Rectangle r = new Rectangle(x, y, w, h);
//      lowerBars.add(r);
//    }
//  }
  
//  void draw(){
//    strokeWeight(1);
    
//    for ( Rectangle bar : lowerBars ) {
//      for ( int i = (int)bar.x; i < bar.x + bar.w; i++ ) {
//        float alphaMax = -255 - (bar.y - topY);
//        float alphaMin =  255 + (bar.y - topY);
//        float alpha = map(i, bar.x, bar.x + bar.w, alphaMin, alphaMax);
//        float step = map(i, bar.x, bar.x + bar.w, 0, 1);
//        color lc = interpolateColor(barColors, step);
    
//        stroke(lc, 255 - abs(alpha));
//        line(i, bar.y, i, bar.y + bar.h);
//      }
      
//      bar.y += speed;
//      bar.x += speed;
//      bar.w -= 2 * speed;

//      if( bar.y > bottomY ) {
//        // Bar at bottom, reset to top
        
//        bar.x = topX;
//        bar.y = topY + maxHeight;
//        bar.w = topWidth;
//        bar.h = 1;
//      } else if( bar.y > bottomY - maxHeight ) {
//        // Bar near bottom
        
//        bar.h -= speed;
//      } else if( bar.h < maxHeight ) {
//        // Bar height just reset and at top
        
//        bar.y -= speed;
//        bar.h += speed;
//      }
//    }
//  }
//}
