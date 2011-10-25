function [X_norm, mu, sigma] = featureNormalize(X)
%FEATURENORMALIZE Normalizes the features in X 
%   FEATURENORMALIZE(X) returns a normalized version of X where
%   the mean value of each feature is 0 and the standard deviation
%   is 1. This is often a good preprocessing step to do when
%   working with learning algorithms.

% You need to set these values correctly

% ====================== YOUR CODE HERE ======================
% Instructions: First, for each feature dimension, compute the mean
%               of the feature and subtract it from the dataset,
%               storing the mean value in mu. Next, compute the 
%               standard deviation of each feature and divide
%               each feature by it's standard deviation, storing
%               the standard deviation in sigma. 
%
%               Note that X is a matrix where each column is a 
%               feature and each row is an example. You need 
%               to perform the normalization separately for 
%               each feature. 
%
% Hint: You might find the 'mean' and 'std' functions useful.
%   

% let's calculate the total number of features  

%mean([1; 2; 3; 4]) returns 2.5

%X(:,1) is the price of the houses
%X(:,2) is the number of bedrooms
%mu(1,1) = accessing the position of mu
%mu(1,2) = 2

% updating the positions of a given matrix's column
%d = [1 2; 3 4; 5 6]
%d(:, 1) = d(:, 1) .- d(:, 1)

% As defined in the class notes, n is the number of features.
n = size(X, 2);

% the given values of mu and sigma as initial zeros
mu = zeros(1, n);
sigma = zeros(1, n);

for index = 1:n
    mu(1, index) = mean(X(:, index));
    X(:, index) = X(:, index) .- mu(1, index);

    sigma(1, index) = std(X(:, index));
    X(:, index) = X(:, index) ./ sigma(1, index);
end;

% updating the value of the return X_norm
X_norm = X;

%disp(mu)
%disp(sigma)

% ============================================================

end
