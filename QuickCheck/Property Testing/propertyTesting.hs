{-# OPTIONS_GHC -Wno-unrecognised-pragmas #-}
{-# HLINT ignore "Use foldr" #-}
{-# HLINT ignore "Use <$>" #-}
{-# LANGUAGE TemplateHaskell #-}

import Test.QuickCheck
import Data.List
import Cp

--1

mulL :: Num a => [a] -> a
mulL [] = 1
mulL (h:t) = h * mulL t

--a)
prop_revMul :: [Int] -> Bool
prop_revMul  = uncurry (==) . split mulL (mulL . reverse)

--b)
prop_singMul :: Int -> Bool
prop_singMul = uncurry (==) . split (mulL . singl) id

--c)
prop_similarMul :: [Int] -> Bool
prop_similarMul = uncurry (==) . split product mulL


--2

data BST = Empty
    | Node BST Int BST
    deriving Show


fromList :: [Int] -> BST
fromList [] = Empty
fromList (x:xs) = Node Empty x (fromList xs)

isBST :: BST -> Bool
isBST Empty = True
isBST (Node l x r) = isBST l && isBST r
                && maybeBigger (Just x) (maybeMax l)
                && maybeBigger (maybeMin r) (Just x)
    where   maybeBigger _ Nothing = True
            maybeBigger Nothing _ = True
            maybeBigger (Just x) (Just y) = x >= y
            maybeMax Empty = Nothing
            maybeMax (Node _ x Empty) = Just x
            maybeMax (Node _ _ r) = maybeMax r
            maybeMin Empty = Nothing
            maybeMin (Node Empty x _) = Just x
            maybeMin (Node l _ _) = maybeMin l


--a)

instance Arbitrary BST where
    arbitrary = genBSTValidBalanced

genBST :: Gen BST
genBST = do
    n <- arbitrary
    return(fromList n)


--b)

genBSTValid :: Gen BST
genBSTValid = do
    n <- arbitrary
    return(fromList (sort n))


prop_isValidBST :: BST -> Bool
prop_isValidBST = isBST

--c)

balanced :: BST -> Bool
balanced Empty = True
balanced (Node l _ r) = balanced l && balanced r && abs (altura l - altura r) <= 1
    where altura Empty = 0
          altura (Node l _ r) = 1 + max (altura l) (altura r) 


prop_isBalanced :: BST -> Bool
prop_isBalanced = balanced

--c, i)

fromList' :: [Int] -> BST
fromList' [] = Empty
fromList' l =
    let size = length l
        half = div size 2
        firstHalf = take half l
        secondHalf = drop half l
        thisNode = head secondHalf
        right = tail secondHalf
    in Node (fromList' firstHalf) thisNode (fromList' right)


genBSTValidBalanced :: Gen BST
genBSTValidBalanced = do
    n <- arbitrary
    return(fromList' (sort n))


--d)

--TODO

--3

find' :: (a -> Bool) -> [a] -> Maybe a
find' f [] = Nothing
find' f (x:xs) = case find' f xs of
            Just k -> Just k
            Nothing -> if f x then Just x else Nothing

--a)

findValid :: (a -> Bool) -> [a] -> Maybe a
findValid f [] = Nothing
findValid f (x:xs) = if f x then Just x else findValid f xs

prop_similarFind :: [Int] -> Bool
prop_similarFind = uncurry (==) . split (find even) (findValid even)

removePredicate :: (a -> Bool) -> [a] -> [a]
removePredicate f [] = []
removePredicate f (h:t)= if f h then removePredicate f t else h : removePredicate f t

prop_onlyValids :: [Int] -> Bool
prop_onlyValids = uncurry (==) . split (findValid even) (findValid even . removePredicate odd)


return []
runTests = $quickCheckAll