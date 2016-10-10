#lang racket

(provide (all-defined-out))

(struct point [x y] #:prefab)#|
  #:guard (λ (x y name)
            (unless (and (number? x) (number? y))
              (error "Not a valid point"))
            (values x y)))|#

(define/contract (distance a b)
  (point? point? . -> . number?)
  (sqrt (+ (sqr (- (point-x a) (point-x b)))
           (sqr (- (point-y a) (point-y b))))))

(module+ test
  (require rackunit)
  (for ([t '(((#s{point 1 1} #{point 1 1}) . 0))])
    (check-equal? (apply distance (car t)) (cdr t) (~a t))))
