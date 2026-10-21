#include "helpers.hpp"

float Helper::inputFloat(std::string message, float minimum, float maximum) {
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
        } else if (input < minimum || input > maximum) 
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

int Helper::inputInteger(std::string message, int minimum, int maximum) {
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
        } else if (input < minimum || input > maximum) 
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