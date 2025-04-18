# -*- coding: utf-8 -*-

import numpy as np
import matplotlib.pyplot as plt

def main():
    # 入力を受け取る
    N = 7
    xs_ave = [1, 2, 3, 4, 5, 6, 7]
    ys_ave = [6, 10, 11, 15, 17, 19, 20]
    xs_min = [1, 2, 3, 4, 5, 6, 7]
    ys_min = [0, 0, 0, 0, 0, 0, 0]
    xs_max = [1, 2, 3, 4, 5, 6, 7]
    ys_max = [10, 15, 20, 23, 27, 29, 31]

    # 平均値、最小値、最大値を計算
    xs_ave = np.array(xs_ave)
    ys_ave = np.array(ys_ave)
    xs_min = np.array(xs_min)
    ys_min = np.array(ys_min)
    xs_max = np.array(xs_max)
    ys_max = np.array(ys_max)

    # 多項式近似（2次がデフォルト）
    degree = 2
    coeffs_ave = np.polyfit(xs_ave, ys_ave, degree)
    coeffs_min = np.polyfit(xs_min, ys_min, degree)
    coeffs_max = np.polyfit(xs_max, ys_max, degree)
    poly = np.poly1d(coeffs_ave)
    poly_min = np.poly1d(coeffs_min)
    poly_max = np.poly1d(coeffs_max)

    # プロット用のx値
    x_fit = np.linspace(1, 7, 100)
    y_ave_fit = poly(x_fit)
    y_min_fit = poly_min(x_fit)
    y_max_fit = poly_max(x_fit)

    # グラフ描画
    plt.scatter(xs_ave, ys_ave, color='green', label='Average Score')
    plt.scatter(xs_min, ys_min, color='orange', label='Minimum Score')
    plt.scatter(xs_max, ys_max, color='purple', label='Maximum Score')
    plt.plot(x_fit, y_ave_fit, color='green', label='Average Fit')
    plt.plot(x_fit, y_min_fit, color='orange', label='Minimum Fit')
    plt.plot(x_fit, y_max_fit, color='purple', label='Maximum Fit')
    plt.title('Relationship between number of experiments and score')
    plt.xlabel('Number of Experiments')
    plt.ylabel('Score')
    plt.legend()
    plt.grid(True)
    plt.show()

if __name__ == '__main__':
    main()
