''' Copyright (c) 2014 Kevin Driessen, see included LICENSE.txt

Basic Python logger

This logger is a simple module for outputting messages with different levels
of severity to the console.

This module is written for py3.4 and should be compatible with older Python
versions. There are no other dependencies.

==============================================================================
U S A G E   G U I D E
==============================================================================
First make sure you have imported this module.

The setSeverity function allows you to set the severity level. The severity
level defines what messages should be printed out and which should not be
printed out. E.G. if the severity is set to 'w', short for 'WARN' only
messages with a severity of 'w' or 'e' get printed out.

The checkSeverity function is meant to stay private, so you should not call
it from the outside of this module.

The first log function is the actual function that logs messages to the
console. The first parameter is a string value that represents the severity.
It's highly recommanded to use the severity constants from this module instead
of just filling in a string. The second parameter is the message that will be
printed out in the following format: [<Severity>] <Message>

The second log function does the same as the first one, but requires only the
message parameter. It just calls the first log function with the 'Debug'
severity.
'''

# Severity constants
DEBUG = 'd'
WARN = 'w'
ERR = 'e'

# Private variables
__severities = {'d': ('DEBUG', 0), 'w': ('WARNING', 1), 'e': ('ERROR', 2)}
__severity = WARN # Default severity is 'WARN'.

def setSeverity(severity):
    # Set's the current severity level for logging.
    if severity in 'e w d'.split():
        __severity = severity
    else:
        print('[LOGGER] Unknown severity "%s".' % (severity))

def __checkSeverity(severity):
    # Returns True if the severity is at the same or higher level then the
    # current severity, otherwise returns False.
    return __severities[severity][1] >= __severities[__severity][1]

def log(severity, message):
    # Logs a message with a specified severity. If the severity level is lower
    # then the current severity of the logger it will not be printed out.
    if __checkSeverity(severity):
        print('[%s] %s' % (__severities[severity][0], message))
        
def log(message):
    # Logs a message with the 'Debug' severity.
    log(DEBUG, message)

# Running a quick test, if the 'Debug' test does not print out it's succesfull.
if __name__ == '__main__':
    log(DEBUG, 'Debug test')
    log(WARN, 'Warning test')
    log(ERR, 'Error test')
    
