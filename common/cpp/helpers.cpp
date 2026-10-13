#include "helpers.hpp"

float Helper::inputFloat(
std::string message, 
float minimum = std::numeric_limits<float>::lowest(), 
float maximum = std::numeric_limits<float>::max()
) {
    float input;

    while (true)
    {
        std::cout << message;
        std::cin >> input;
            
        if (std::cin.fail()) //check for invalid input, i.e. string instead of int or some other stuff.
        {
            std::cout << "Invalid input, please enter a number.\n\n";
            std::cin.clear();
            std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');

            continue;
        } else if (minimum <= input && input <= maximum) 
        {
            std::printf("Invalid input, please enter a number between %d and %d.\n\n", minimum, maximum);
            std::cin.clear();
            std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');

            continue;
        }
        break;
    }
    return input;
}

int Helper::inputInteger(
std::string message, 
int minimum = std::numeric_limits<int>::lowest(), 
int maximum = std::numeric_limits<int>::max()
) {
    int input;

    while (true)
    {
        std::cout << message;
        std::cin >> input;
            
        if (std::cin.fail()) //check for invalid input, i.e. string instead of int or some other stuff.
        {
            std::cout << "Invalid input, please enter a number.\n\n";
            std::cin.clear();
            std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');

            continue;
        } else if (minimum <= input && input <= maximum) 
        {
            std::printf("Invalid input, please enter a number between %d and %d.\n\n", minimum, maximum);
            std::cin.clear();
            std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');

            continue;
        }
        break;
    }
    
    return input;
}

void Helper::print(std::string message) {std::cout << message << "\n";};