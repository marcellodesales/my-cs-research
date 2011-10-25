function J = computeCostMulti(X, y, theta)
%COMPUTECOSTMULTI Compute cost for linear regression with multiple variables
%   J = COMPUTECOSTMULTI(X, y, theta) computes the cost of using theta as the
%   parameter for linear regression to fit the data points in X and y

% Initialize some useful values
m = length(y); % number of training examples

% You need to return the following variables correctly 

% ====================== YOUR CODE HERE ======================
% Instructions: Compute the cost of a particular choice of theta
%               You should set J to the cost.

% You need to return the following variables correctly 

% Theta now has the values of all the features of X. X(:, 1) is all 1's. That mean
% X has n+1 features as the class notes.
% predictions of hypothesis on all m should just be the multiplication of thetas.
predictions = theta * X;

% the squared errors should be as usual
sqrErrors = (predictions - y) .^ 2;

% the result of the function is as it is defined regularly.
J = (1/(2*m)) * sum(sqrErrors);

% =========================================================================

end
