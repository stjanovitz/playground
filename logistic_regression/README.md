Test of naive implementation of linear regression. Uses batch gradient descent
instead of stochastic gradient descent.

Implemented logistic regression model results:
*	10,000 iterations:
 *	E_in: 0.5847
 *	Classification error on training data: 0.3092
 *	Classification error on test data: 0.3172
*	100,000 iterations:
 *	E_in: 0.4937
 *	Classification error on training data: 0.2237
 *	Classification error on test data: 0.2069
*	1,000,000 iterations:
 *	E_in: 0.4354
 *	Classification error on training data: 0.1310
 *	Classification error on test data: 0.1513

The model generalizes well, especially as the number of maximum iterations increases.

glmfit results:
*	Classification error on training data: 0.1711
*	Classification error on test data: 0.1034

While glmfit had a higher classification error on the training data than
my logistic regression model, it had a lower classification error on out
of sample data. glmfit also computed the weights much faster than my
logistic regression model:  the weights were computed by glmfit in less
than second, whereas the weights computed by logistic_reg took longer,
up to a minute for one million iterations of the algorithm.