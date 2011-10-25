function J = computeCost(X, y, theta)
%COMPUTECOST Compute cost for linear regression
%   J = COMPUTECOST(X, y, theta) computes the cost of using theta as the
%   parameter for linear regression to fit the data points in X and y

% ====================== YOUR CODE HERE ======================
% Instructions: Compute the cost of a particular choice of theta
%               You should set J to the cost.

% The function is the usual one:
% J = 1/2m * sum[(theta * X(i) - y(i))^2] for every (i) of size(training)

% The hypothesis function is given in the PDF as
% theta = theta(0) + theta(1)*X(1)

% Initialize some useful values
m = length(y); % number of training examples as it is in y

% You need to return the following variables correctly 
% As theta is a matrix of zeros and X has the first column of ones
% predictions of hypothesis on all m, such that we consider the theta elements.
predictions = theta(1,:) .+ theta(2,:) .* X(:,2);

% the squared errors should be as usual
sqrErrors = (predictions - y) .^ 2; 

% the result of the function is as it is defined regularly.
J = (1/(2*m)) * sum(sqrErrors);

% =========================================================================

end
