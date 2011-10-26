function [theta] = normalEqn(X, y)
%NORMALEQN Computes the closed-form solution to linear regression 
%   NORMALEQN(X,y) computes the closed-form solution to linear 
%   regression using the normal equations.

theta = zeros(size(X, 2), 1);

% ====================== YOUR CODE HERE ======================
% Instructions: Complete the code to compute the closed form solution
%               to linear regression and put the result in theta.
%

% ---------------------- Sample Solution ----------------------

% As defined in one of the classes related to the normal equation,
% we will be using the function 'pinv', which returns the 
% sudoinverse of a matrix.

theta = pinv(X' * X) * X' * y;

% -------------------------------------------------------------


% ============================================================

end
