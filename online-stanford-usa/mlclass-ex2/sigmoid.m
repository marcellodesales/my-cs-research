function g = sigmoid(z)
%SIGMOID Compute sigmoid functoon
%   J = SIGMOID(z) computes the sigmoid of z.

% You need to return the following variables correctly 
g = zeros(size(z));

% ====================== YOUR CODE HERE ======================
% Instructions: Compute the sigmoid of each value of z (z can be a matrix,
%               vector or scalar).

% To define for any matrix, vector or scalar.

% The sigmod formula is 1 / (1 + e ^ -z)
negativeZ = -z;
exponent = e .^ negativeZ;
ones = ones(size(z));
g = ones ./ (ones .+ (exponent));

% =============================================================

end
