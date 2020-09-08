package edu.espe.controlGastos.receivers;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;

import edu.espe.controlGastos.jobs.BudgetJob;
import edu.espe.controlGastos.jobs.TransactionJob;


public class MyJobCreator implements JobCreator {

    @Override
    @Nullable
    public Job create(@NonNull String tag) {
        switch (tag) {
            case TransactionJob.TAG:
                return new TransactionJob();
            case BudgetJob.TAG:
                return new BudgetJob();
            default:
                return null;
        }
    }
}