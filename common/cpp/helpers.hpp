#pragma once
#include <string>
#include <iostream>
#include <limits>

class Helper {
    public:
    static float inputFloat
    (std::string message, 
    float minimum = std::numeric_limits<float>::lowest(), 
    float maximum = std::numeric_limits<float>::max());

    static int inputInteger
    (std::string message, 
    int minimum = std::numeric_limits<int>::lowest(), 
    int maximum = std::numeric_limits<int>::max());
    
    static void print(std::string message);
};